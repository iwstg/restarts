package com.example.demo.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "UserInfo")
public class userEntity {
    @Id
    private String userID;


    private String userName;

    private String userEmail;

    private String userPassword;
}
