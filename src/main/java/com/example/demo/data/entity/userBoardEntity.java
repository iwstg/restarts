package com.example.demo.data.entity;

import com.example.demo.data.dto.UserBoardDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "UserBoard")
public class userBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardnum;

    private String title;
    private String contents;


    private String create_user;

    private String modified_user;


    private String modify_pwd;

    private LocalDateTime create_date;

    private LocalDateTime modified_date;

    @Override
    public String toString() {
        return "userBoardEntity{" +
                "boardnum=" + boardnum +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", create_user='" + create_user + '\'' +
                ", modified_user='" + modified_user + '\'' +
                ", modify_pwd='" + modify_pwd + '\'' +
                ", create_date=" + create_date +
                ", modified_date=" + modified_date +
                '}';
    }
}
