package vn.phat.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.phat.dto.TestCondition;
import vn.phat.entites.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long>, TestCustomRepository{

    @Query(value = "SELECT * FROM TEST WHERE NAME LIKE %:#{#condition.name}%"
            , nativeQuery = true)
    Page<TestEntity> getDataByCondition(@Param("condition") TestCondition condition, Pageable pageable);

}
