package vn.phat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.phat.dto.PageWrapper;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.services.TestService;

import java.util.ArrayList;
import java.util.function.Supplier;

@SpringBootTest
@Slf4j
class PhatTest {
    @Autowired
    TestService testService;

    @Test
    void createTest(){

        Supplier<Long> create = ()-> testService.save(new TestEntity()).getId();
        action(1, create, "Create");
        Assertions.assertNotNull(create.get());
    }

    @Test
    void updateTest(){
        Supplier<Long> update = ()->{
            TestEntity en = testService.save(new TestEntity());
            return testService.save(en).getId();
        };
        action(1, update,"Update");
        Assertions.assertNotNull(update.get());
    }

    void action(int n, Supplier<Long> function, String action){
        long start = System.currentTimeMillis();
        for(int i=0; i< n; i++){
            function.get();
        }
        log.info("{} took {} ms", action, System.currentTimeMillis() - start);
    }

    @Test
    void searchTest(){
        TestCondition condition = new TestCondition();
        condition.setName("");
        condition.setCondition(new ArrayList<>());
        long start = System.currentTimeMillis();
        PageWrapper<TestEntity> result = testService.search(condition, 50, 100);
        long took = System.currentTimeMillis() - start;
        log.info("Took: {}", took);
        Assertions.assertNotNull(result.getDatas());

    }
}
