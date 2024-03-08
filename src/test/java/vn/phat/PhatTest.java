package vn.phat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.phat.services.TestService;

@SpringBootTest
@Slf4j
class PhatTest {
    @Autowired
    TestService testService;

}
