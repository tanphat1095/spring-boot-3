package vn.phat.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.repositories.TestRepository;
import vn.phat.services.TestService;

import java.util.List;

@Service
public class TestServiceImpl extends BaseServiceImpl<Long, String, TestCondition, TestEntity, TestRepository>
        implements TestService {
    private TestRepository test;

    @Autowired
    void setTestRepo(TestRepository test){
        this.test = test;
    }

    @Override
    TestRepository getRepository() {
        return this.test;
    }

    @Override
    Page<TestEntity> getPage(TestCondition condition, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return test.getDataByCondition(condition, pageable);
    }

    @Override
    String getAuthor() {
        return "Test";
    }

    public List<TestEntity> getFromCriterialQuery(TestCondition condition){
        return getRepository().criteriaQuery(condition);
    }
}
