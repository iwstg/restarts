package com.example.demo.data.repository;


import com.example.demo.data.entity.userBoardEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface userBoardRepository extends JpaRepository<userBoardEntity, String> {

    @Query(value = "SELECT * FROM mysqltest.user_board WHERE boardnum = :num", nativeQuery = true)
    userBoardEntity findByboardnum(@Param("num") long num);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mysqltest.user_board SET title = :title, contents = :contents, modified_user = :user, modified_date = :time WHERE user_board.boardnum = :num", nativeQuery = true)
    void UpdateBoard(@Param("title") String title, @Param("contents") String contents, @Param("user") String user, @Param("num") int num, @Param("time")LocalDateTime time);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM mysqltest.user_board WHERE boardnum= :id", nativeQuery = true)
    void deleteBybaordnum(@Param("id") int num);
}
