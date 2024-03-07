package vn.phat.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import vn.phat.dto.BaseCondition;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseMasterRepository<E, I extends Serializable, C extends BaseCondition> extends JpaRepository<E, I> {

    Page<E> criteriaQuery(C condition, Pageable pageable);
}