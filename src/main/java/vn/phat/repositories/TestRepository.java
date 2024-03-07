package vn.phat.repositories;

import org.springframework.stereotype.Repository;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;

@Repository
public interface TestRepository extends BaseMasterRepository<TestEntity, Long, TestCondition>{
}
