package vn.phat.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.repository.NoRepositoryBean;
import vn.phat.dto.BaseCondition;
import vn.phat.repositories.BaseSearchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public abstract class BaseSearchRepositoryImpl<E, C extends BaseCondition> implements BaseSearchRepository<E, C> {

    private EntityManager entityManager;

    void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<E> criteriaQuery(C condition) {
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
        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }

    abstract Class<E> getEntityClass();

}
