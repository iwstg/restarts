package com.example.demo.data.dto;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfilChangeDTO {
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userIntroduce;
    private String userProfilImg;

    @Override
    public String toString() {
        return "UserProfilChangeDTO{" +
                "userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userIntroduce='" + userIntroduce + '\'' +
                ", userProfilImg='" + userProfilImg + '\'' +
                '}';
    }
}