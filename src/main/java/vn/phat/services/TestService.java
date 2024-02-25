package vn.phat.services;

import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;

import java.util.List;

public interface TestService extends BaseService<Long, String, TestCondition, TestEntity>{

    List<TestEntity> getFromCriterialQuery(TestCondition condition);
}
