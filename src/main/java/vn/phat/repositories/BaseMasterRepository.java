package vn.phat.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import vn.phat.dto.BaseCondition;

import java.io.Serializable;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
@NoRepositoryBean
public interface BaseMasterRepository<E, I extends Serializable, U extends Enum<U>, C extends BaseCondition<U>> extends JpaRepository<E, I> {

    Page<E> criteriaQuery(C condition, Pageable pageable);
}
