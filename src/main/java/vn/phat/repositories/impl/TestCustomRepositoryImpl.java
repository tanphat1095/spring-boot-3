package vn.phat.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.repositories.TestCustomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCustomRepositoryImpl implements TestCustomRepository {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<TestEntity> testCriteriaQuery(TestCondition condition) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TestEntity> query = cb.createQuery(TestEntity.class);
        Root<TestEntity> root = query.from(TestEntity.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(condition).ifPresent(cond ->{
            cond.getCondition().forEach(c -> {
                Optional.ofNullable(c).ifPresent(con->{
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
}
