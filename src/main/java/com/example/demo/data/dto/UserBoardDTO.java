package com.example.demo.data.dto;


import com.example.demo.data.entity.userBoardEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardDTO {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String contents;

    @NotBlank(message = "패스워드는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9]).{4}", message = "비밀번호는 4자리 숫자만을 사용하세요.")
    private String modify_pwd;

    @Override
    public String toString() {
        return "UserBoardDTO{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", modify_pwd='" + modify_pwd + '\'' +
                '}';
    }
}

