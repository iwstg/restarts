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

    private String userIntroduce;

    private String userProfilImg;

    private LocalDateTime userRegistDate;

    /**
     * [2025-02-11] userRecentConnectionDate는 이후 로그인 시 업데이트 되도록 설정 필요
     */
    private LocalDateTime userRecentConnectionDate;

}
