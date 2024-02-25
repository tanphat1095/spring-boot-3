package vn.phat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestCondition {
    private String name;
    private List<Condition> condition;
}
