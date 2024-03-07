package vn.phat.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.phat.dto.PageWrapper;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;
import vn.phat.res.BaseRes;
import vn.phat.services.TestService;

@RestController
@RequestMapping("/test")
public class TestRest {

    private TestService testService;

    @Autowired
    void setTestService(TestService testService){
        this.testService = testService;
    }
    @PostMapping(value = "/search")
    BaseRes<PageWrapper<TestEntity>> search(@RequestBody TestCondition condition, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize, HttpServletRequest req, HttpServletResponse res ){
        return new BaseRes<>(testService.search(condition, page, pageSize), System.currentTimeMillis());
    }

}
