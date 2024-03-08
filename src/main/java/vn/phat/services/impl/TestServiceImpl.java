package vn.phat.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.enumdef.TestSearchEnum;
import vn.phat.repositories.TestRepository;
import vn.phat.services.TestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<Long, String, TestSearchEnum, TestCondition, TestEntity, TestRepository>
        implements TestService {
    private TestRepository test;

    @Autowired
    void setTestRepo(TestRepository test) {
        this.test = test;
    }

    @Override
    TestRepository getRepository() {
        return this.test;
    }

    @Override
    String getAuthor() {
        return "Test";
    }

}
