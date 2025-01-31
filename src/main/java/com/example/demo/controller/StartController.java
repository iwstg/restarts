package com.example.demo.controller;

import com.example.demo.data.dto.LoginPageDTO;
import com.example.demo.data.dto.RegisterPageDTO;
import com.example.demo.data.dto.UserInfoDTO;
import com.example.demo.service.userControlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class StartController {

    userControlService userService;

    @Autowired
    public StartController(userControlService userService){
        this.userService=userService;
    }

    /**
     * [2025-01-14] 메인페이지를 로그인페이지로 리다이랙션 해주는 곳
     * 이후, session 기능을 넣고 나서부터는 로그인페이지거나 세션에 맞는 페이지로 리다이랙션 기능 필요
     *
     * [2025-01-31] 세션 기능 추가..예정 시도해보는중 새로운 브런치
     */
    @GetMapping("/")
    public String goToLoginPage(@SessionAttribute(name="userId", required = false)String userID,Model model) {
        System.out.println("로그인 페이지로 ( / -> Loginpage ) ");

        System.out.println("[Controller] 세션 확인 로직 실행");


        if(userID == null){
            System.out.println("[Controller] 세션에 가입된 유저정보 없음");
        }else{
            UserInfoDTO userinfo = userService.ReturnUserInfoUseID(userID);
            System.out.println("[Controller] 세션으로 등록된 유저정보 확인");
            System.out.println(userinfo.toString());
            return "MainPage";
        }
//        if(userDTO.getUserID() == null){
//            System.out.println("[Controller] 세션 없음. 로그인 하지않은 상태");
//        }else{
//            System.out.println("[Controller] 로그인됨. 사용자: " + userDTO.getUserID());
//        }
//
//        UserInfoDTO loginUser = userService.ReturnUserInfoUseID(userDTO.getUserID());
//        System.out.println("[Controller] 세션 ? 체크 ? " + loginUser.toString());

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
     * [2025-01-31] 로그인페이지에서 입력받은 내용과 일치하는 정보가 DB에 존재할경우 로그인 진행
     */
    @PostMapping("LoginTry")
    public String runLoginRequest(LoginPageDTO form, HttpServletRequest req){
        if(userService.userTryToLogin(form)) {
            //로그인 성공 시

            HttpSession session = req.getSession();
            session.setAttribute("userId", form.getUserLoginId());
            session.setMaxInactiveInterval(60);
            System.out.println("[Controller] 세션 : " + session.getAttribute("userId"));
//            LoginPageDTO login = userService.userTryToLogin(form)?form:null;
//            if (login == null){
//
//            }
            return "MainPage";
        }else
            return "LoginPage";
    }


    @PostMapping("MainPageLogOut")
    public String TryToLogOut(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        session.invalidate();
        System.out.println("[Controller] 세션 박살! 로그인페이지로!");
        return "LoginPage";
    }

    /**
     * [2025-01-14] 회원가입페이지에서 전달받은 form 의 내용을 dto에 맞게 전달받음.
     * 해당 기능은 form에서 받아온 내용으로 회원가입 dao 필요.
     * #option 패스워드 검증 프론트엔드도 추가
     *
     * [2025-01-30] 들어온 정보를 비교하여 회원가입에 성공 시 성공팝업 실패시 실패팝업 추가 필요
     */
    @PostMapping("RegisterRequest")
    public String runRegisterRequest(RegisterPageDTO form) {
        System.out.println(form.toString());
        // form 에서 받아온 DTO로 회원가입 서비스 진행
        userService.userRegistService(form);
        System.out.println("[Controller] form 데이터 전송 ( Controller -> Service");
        // 들어온 데이터가 정상이고 처리도 정상으로 됐으면 안내페이지로
        return "LoginPage";
    }



}
