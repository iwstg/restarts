package com.example.demo.controller;

import com.example.demo.data.dto.RegisterPageDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StartController {

    /**
     * [2025-01-14] 메인페이지를 로그인페이지로 리다이랙션 해주는 곳
     * 이후, session 기능을 넣고 나서부터는 로그인페이지거나 세션에 맞는 페이지로 리다이랙션 기능 필요
     */
    @GetMapping("/")
    public String goToLoginPage() {
        System.out.println("로그인 페이지로 ( / -> Loginpage ) ");
        return "LoginPage";
    }


    /**
     * [2025-01-14] 로그인페이지에서 회원가입페이지로 리다이랙션 해주는 곳
     * 해당 기능은 추가할 내용이 없어보임.
     */
    @GetMapping("RegisterPage")
    public String goToRegistPage() {
        System.out.println("회원가입 페이지로 ( RegistPage -> Registpage ) ");
        return "RegisterPage";
    }


    /**
     * [2025-01-14] 회원가입페이지에서 전달받은 form 의 내용을 dto에 맞게 전달받음.
     * 해당 기능은 form에서 받아온 내용으로 회원가입 dao 필요.
     * #option 패스워드 검증 프론트엔드도 추가
     */
    @PostMapping("RegisterRequest")
    public String runRegisterRequest(RegisterPageDTO form) {
        System.out.println(form.toString());
        // form 에서 받아온 DTO로 회원가입 서비스 진행
        return "LoginPage";
    }



}
