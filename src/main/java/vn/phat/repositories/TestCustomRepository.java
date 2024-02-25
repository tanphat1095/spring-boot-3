package vn.phat.repositories;

import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;

import java.util.List;

public interface TestCustomRepository {
    List<TestEntity> testCriteriaQuery(TestCondition condition);
}
