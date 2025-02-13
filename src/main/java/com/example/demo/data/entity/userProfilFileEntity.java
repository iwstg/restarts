package com.example.demo.data.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class userProfilFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;

    private String filename;
    private String realname;
    private String fileURL;
}
