package com.example.demo.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "UserInfo")
public class userEntity {
    @Id
    private String userID;

    private String userName;

    private String userEmail;

    private String userPassword;

    @ColumnDefault("")
    private String userIntroduce;

    private LocalDateTime userRegistDate;

    private LocalDateTime userRecentConnectionDate;

}
