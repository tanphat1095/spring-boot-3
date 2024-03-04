package vn.phat.repositories.impl;

import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.repositories.TestCustomRepository;

public class TestCustomRepositoryImpl extends BaseSearchRepositoryImpl<TestEntity, TestCondition> implements TestCustomRepository {

    @Override
    Class<TestEntity> getEntityClass() {
        return TestEntity.class;
    }
}
