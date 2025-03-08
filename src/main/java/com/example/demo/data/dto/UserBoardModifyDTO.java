package com.example.demo.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardModifyDTO {
    private String title;
    private String contents;
    private String modify_pwd;

    @Override
    public String toString() {
        return "UserBoardModifyDTO{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", modify_pwd='" + modify_pwd + '\'' +
                '}';
    }
}
