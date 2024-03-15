package vn.phat.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.phat.entites.TestEntity;

@Component
@Slf4j
public class TestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TestEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TestEntity entity = (TestEntity) target;
        log.error("DEBUG Validator");
    }
}
