package com.example.demo.data.repository;

import com.example.demo.data.entity.userEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userEntity, String> {

    /**
     *  [2025-03-08] Jpa 안에 커스텀 Query 정의로 사용자 이름 변경부분 메소드화
     */

    @Transactional
    @Modifying
    @Query(value = "Update mysqltest.user_info set user_name = :name WHERE user_info.userid = :id", nativeQuery = true)
    void UpdateName(@Param("name") String name, @Param("id") String id);
}
