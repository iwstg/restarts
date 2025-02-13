package com.example.demo.data.repository;

import com.example.demo.data.entity.userProfilFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userProfilFileRepository extends JpaRepository<userProfilFileEntity, Integer> {
    userProfilFileEntity findByFno(int fno);
}
