package vn.phat.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import vn.phat.dto.BaseCondition;
import vn.phat.dto.Condition;
import vn.phat.repositories.BaseMasterRepository;
import vn.phat.util.ConvertNameUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class BaseMasterRepositoryImpl<E, I extends Serializable, U extends Enum<U>, C extends BaseCondition<U>> extends SimpleJpaRepository<E, I> implements BaseMasterRepository<E, I, U, C> {

    final EntityManager entityManager;

    final JpaEntityInformation<E, ?> entityInformation;

    public BaseMasterRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    private Class<E> getEntityClass() {
        return this.entityInformation.getJavaType();
    }

    @Override
    public Page<E> criteriaQuery(C condition, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        List<E> data = queryList(condition, pageable, cb);
        Long count = queryCount(condition, cb);
        return new PageImpl<>(data, pageable, count);
    }

    protected Long queryCount(C condition, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<E> root = query.from(getEntityClass());
        Predicate[] predicates = getCondition(condition, root, criteriaBuilder);
        query.select(criteriaBuilder.count(root)).where(predicates);
        return entityManager.createQuery(query).getSingleResult();
    }

    protected List<E> queryList(C condition, Pageable pageable, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<E> query = criteriaBuilder.createQuery(getEntityClass());
        Root<E> root = query.from(getEntityClass());
        query.select(root);
        Predicate[] predicates = getCondition(condition, root, criteriaBuilder);
        query.where(predicates);
        return entityManager.createQuery(query).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
    }

    protected Predicate[] getCondition(C condition, Root<?> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Consumer<Condition<U>> processCondition = cond -> {
            U field = cond.getFieldName();
            String fileName = ConvertNameUtil.snakeToCamel(field.name());
            Expression<String> fieldName = root.get(fileName);
            Predicate pre = switch (cond.getOperator()) {
                case LIKE -> criteriaBuilder.like(fieldName, "%" + cond.getValue() + "%");
                case EQUAL -> criteriaBuilder.equal(fieldName, cond.getValue());
                case GREATER -> criteriaBuilder.greaterThan(fieldName, cond.getValue());
                case LESS -> criteriaBuilder.lessThan(fieldName, cond.getValue());
                default -> throw new UnsupportedOperationException();
            };
            predicates.add(pre);
        };
        Consumer<C> handleListCondition = c -> Optional.ofNullable(c.getCondition()).orElseGet(ArrayList::new).forEach(processCondition);
        Optional.ofNullable(condition).ifPresent(handleListCondition);
        return predicates.toArray(new Predicate[0]);
    }

}
