package com.example.demo.data.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPageDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;

    @Override
    public String toString() {
        return "RegisterPageDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
