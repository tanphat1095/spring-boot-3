package vn.phat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
@Getter
@Setter
public class BaseCondition<E extends Enum<E>> {
    private List<Condition<E>> condition;// condition and
    private String keySearch; // condition or
}
