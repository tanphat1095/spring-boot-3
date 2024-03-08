package vn.phat.dto;

import lombok.Getter;
import lombok.Setter;
import vn.phat.enumdef.Operator;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
@Getter
@Setter
public class Condition<E extends Enum<E>> {

    private E fieldName;
    private Operator operator;
    private String value;

}
