package vn.phat.dto;

import lombok.Getter;
import lombok.Setter;
import vn.phat.enumdef.Operator;

@Getter
@Setter
public class Condition {

    private String fieldName;
    private Operator operator;
    private String value;

}
