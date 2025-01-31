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

    @Override
    public String toString() {
        return "LoginPageDTO{" +
                "UserLoginId='" + UserLoginId + '\'' +
                ", UserLoginPassword='" + UserLoginPassword + '\'' +
                '}';
    }
}
