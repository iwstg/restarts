package com.example.demo.data.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginPageDTO {
    private String UserLoginId;
    private String UserLoginPassword;

    public String getUserLoginId() {
        return UserLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        UserLoginId = userLoginId;
    }

    public String getUserLoginPassword() {
        return UserLoginPassword;
    }

    public void setUserLoginPassword(String userLoginPassword) {
        UserLoginPassword = userLoginPassword;
    }

    @Override
    public String toString() {
        return "LoginPageDTO{" +
                "UserLoginId='" + UserLoginId + '\'' +
                ", UserLoginPassword='" + UserLoginPassword + '\'' +
                '}';
    }
}
