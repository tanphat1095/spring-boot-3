package vn.phat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.phat.dto.PageWrapper;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.services.TestService;

@SpringBootTest
class PhatTest {
    @Autowired
    TestService testService;

    @Test
    void createTest(){
        TestEntity en = new TestEntity();
        Assertions.assertNotNull(testService.save(en).getId());
    }

    @Test
    void updateTest(){
        TestEntity en = testService.save(new TestEntity());
        Assertions.assertNotNull(testService.save(en).getUpdatedBy());
    }

    @Test
    void searchTest(){
        TestCondition condition = new TestCondition();
        condition.setName("");
        PageWrapper<TestEntity> result = testService.search(condition, 0, 2);
        Assertions.assertNotNull(result.getDatas());

    }
}
