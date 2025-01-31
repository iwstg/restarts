package com.example.demo.data.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPageDTO {
    private String UserId;
    private String UserName;
    private String UserEmail;
    private String UserPassword;

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
