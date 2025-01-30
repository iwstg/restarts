package com.example.demo.data.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPageDTO {
    private String UserId;
    private String UserName;
    private String UserEmail;
    private String UserPassword;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    @Override
    public String toString() {
        return "RegisterPageDTO{" +
                "UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                '}';
    }
}
