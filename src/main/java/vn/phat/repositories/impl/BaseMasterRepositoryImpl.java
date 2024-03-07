package vn.phat.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import vn.phat.dto.BaseCondition;
import vn.phat.repositories.BaseMasterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BaseMasterRepositoryImpl<E, C extends BaseCondition> extends SimpleJpaRepository<E, Long> implements BaseMasterRepository<E, Long, C> {

    final EntityManager entityManager;

    final JpaEntityInformation<E,?> entityInformation;

    public BaseMasterRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }
    private Class<E> getEntityClass(){
        return this.entityInformation.getJavaType();
    }
    @Override
    public Page<E> criteriaQuery(C condition, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(getEntityClass());
        Root<E> root = query.from(getEntityClass());
        query.select(root);
        List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(condition).ifPresent(cond->{
            cond.getCondition().forEach(c ->{
                Optional.ofNullable(c).ifPresent(con ->{
                    Expression<String> fieldName = root.get(con.getFieldName());
                    Predicate pre = switch(con.getOperator()){
                        case EQUAL -> cb.equal(fieldName, con.getValue());
                        case LIKE -> cb.like(fieldName, "%"+ con.getValue() + "%");
                        case GREATER -> cb.greaterThan(fieldName, con.getValue());
                        case LESS -> cb.lessThan(fieldName, con.getFieldName());
                        default -> throw new UnsupportedOperationException();
                    };
                    predicates.add(pre);
                });
            });
        });
        Predicate [] preArr = predicates.toArray(new Predicate[0]);
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<E> rootCount = countQuery.from(getEntityClass());
        countQuery.select(cb.count(rootCount)).where(preArr);
        query.where(preArr);
        Long count = entityManager.createQuery(countQuery).getSingleResult();
        return new PageImpl<>(entityManager.createQuery(query).setFirstResult((int)pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList(), pageable, count);
    }
}
