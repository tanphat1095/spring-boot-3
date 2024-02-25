package vn.phat.rest;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.res.BaseRes;
import vn.phat.res.ListObject;
import vn.phat.services.TestService;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestRest {

    private TestService testService;

    @Autowired
    void setTestService(TestService testService){
        this.testService = testService;
    }
    @PostMapping("/list")
    public BaseRes<ListObject<TestEntity>> getList(@RequestBody @Nullable TestCondition condition){
        long start = System.currentTimeMillis();
        List<TestEntity> list = testService.getFromCriterialQuery(condition);
        ListObject<TestEntity> data = new ListObject<>(list, list.size());
        return new BaseRes<>(data, start);

    }

}
