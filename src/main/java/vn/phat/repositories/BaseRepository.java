package vn.phat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import vn.phat.dto.BaseCondition;

@NoRepositoryBean
public interface BaseRepository<E, C extends BaseCondition> extends JpaRepository<E, Long>, BaseSearchRepository<E, C> {
}
