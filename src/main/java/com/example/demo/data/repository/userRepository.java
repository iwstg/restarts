package com.example.demo.data.repository;

import com.example.demo.data.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<userEntity, String> {
}
