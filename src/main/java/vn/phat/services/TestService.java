package vn.phat.services;

import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.enumdef.TestSearchEnum;

public interface TestService extends BaseService<TestSearchEnum,Long, String, TestCondition, TestEntity>{
}
