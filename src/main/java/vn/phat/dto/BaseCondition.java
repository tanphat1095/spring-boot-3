package vn.phat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseCondition<E extends Enum<E>> {
    private List<Condition<E>> condition;// condition and
    private String keySearch; // condition or
}
