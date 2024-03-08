package vn.phat.rest;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.enumdef.TestSearchEnum;
import vn.phat.services.BaseService;

@RestController
@RequestMapping("/test")
public class TestRest extends BaseRest<Long, String, TestSearchEnum, TestCondition, TestEntity> {
    @Autowired
    @Override
    <S extends BaseService<TestSearchEnum, Long, String, TestCondition, TestEntity>> void setService(S baseService) {
        this.service = baseService;
    }

    @Override
    <V extends Validator> V getValidator() {
        return null;
    }

    @Override
    Class<TestEntity> getClassEntity() {
        return TestEntity.class;
    }
}
