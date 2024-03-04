package vn.phat.repositories;

import vn.phat.dto.BaseCondition;

import java.util.List;

public interface BaseSearchRepository<E,C extends BaseCondition> {
    List<E> criteriaQuery(C condition);
}
