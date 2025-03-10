package com.example.demo.controller;

import com.example.demo.data.dto.*;
import com.example.demo.service.userBoardService;
import com.example.demo.service.userProfilImgService;
import com.example.demo.service.userControlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Pattern;

@Controller
public class StartController {

    userControlService userService;
    userProfilImgService userProfilService;
    userBoardService userBoardserv;

    @Autowired
    public StartController(userControlService userService, userProfilImgService userProfilService, userBoardService userBoardserv){
        this.userService =userService;
        this.userProfilService =userProfilService;
        this.userBoardserv =userBoardserv;
    }

    /**
     * [2025-01-14] 메인페이지를 로그인페이지로 리다이랙션 해주는 곳
     * 이후, session 기능을 넣고 나서부터는 로그인페이지거나 세션에 맞는 페이지로 리다이랙션 기능 필요
     *
     * [2025-01-31] 세션 기능 추가..예정 시도해보는중 새로운 브런치
     */
    @GetMapping("/")
    public String goToLoginPage(@SessionAttribute(name="userId", required = false)String userID,Model model) {
        System.out.println("[Controller] 세션 확인 로직 실행");
        if(userID == null){
            System.out.println("[Controller] 세션에 가입된 유저정보 없음");
            System.out.println("로그인 페이지로 ( / -> Loginpage ) ");
        }else{
            UserProfilDTO userinfo = userService.ReturnUserALLInfoUseID(userID);
            System.out.println("[Controller] 세션으로 등록된 유저정보 확인");
            System.out.println(userinfo.toString());
            model.addAttribute("userinfo", userinfo);
            return "MainPage";
        }
        return "LoginPage";
    }

    /**
     * [2025-02-04] 메인페이지 접근 시 Session 정보의 ID로 값을 불러와 해당 계정의 게시글 및 사용자 정보를 불러옴
     */
    @GetMapping("/MainPage")
    public String MainPageCallResource(@SessionAttribute(name="userId", required = false)String userID, Model model){

        UserProfilDTO userProfilinfo = userService.ReturnUserProfilInfoUseID(userID);

        //회원 정보내용
        model.addAttribute("userProfil", userProfilinfo);


        //게시글 내용



        return "메인페이지 정보 전달완료";
    }



    /**
     * [2025-01-14] 로그인페이지에서 회원가입페이지로 리다이랙션 해주는 곳
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
    public String runLoginRequest(@RequestBody LoginPageDTO form, HttpServletRequest req){
        if(userService.userTryToLogin(form)) {
            //로그인 성공 시

            HttpSession session = req.getSession();
            session.setAttribute("userId", form.getUserLoginId());
            session.setMaxInactiveInterval(3600);
            System.out.println("[Controller] 세션 : " + session.getAttribute("userId"));
            userService.ChangeUserRecentConnectionTime(form.getUserLoginId());
            return "MainPage";
        }else
            return "LoginPage";
    }


    @PostMapping("MainPageLogOut")
    public String TryToLogOut(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        String sessionID = (String)session.getAttribute("userId");
        userService.ChangeUserRecentConnectionTime(sessionID);
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
    public String runRegisterRequest(@RequestBody RegisterPageDTO form) {
        System.out.println(form.toString());
        System.out.println(form);
        // Form 에서 받아온 회원 정보가 요구사항에 맞는지 확인
        if (!userService.CheckDuplEmail(form.getUserEmail()) &&  // 이메일 중복 체크
                Pattern.matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", form.getUserPassword()) && // 패스워드 유효성 체크
                Pattern.matches("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", form.getUserEmail()) && // 이메일 유효성 체크
                Pattern.matches("^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", form.getUserName())) { // 닉네임 유효성 체크
            // form 에서 받아온 DTO로 회원가입 서비스 진행
            userService.userRegistService(form);
            System.out.println("[Controller] form 데이터 전송 ( Controller -> Service");
            // 들어온 데이터가 정상이고 처리도 정상으로 됐으면 안내페이지로
            return "LoginPage";
        } else {
            System.out.println("조건에 맞지않습니다.");
            return "LoginPage";
        }
    }

    /**
     *  [2025-02-11] 사용자 프로필 정보 수정
     *  닉네임 변경, 비밀번호 변경, 이메일 변경, 프로필 사진 변경, 자기소개 변경 기능 필요
     */

    // 유저정보 수정 페이지 접근
    @GetMapping("ChangeUserInfo")
    public String changeuserinfopage(@SessionAttribute(name="userId", required = false)String userID, Model model){
        UserProfilDTO userProfilinfo = userService.ReturnUserProfilInfoUseID(userID);
        model.addAttribute("userInfo", userProfilinfo);
        return "유저정보 수정 페이지입니다.";
    }

    @PostMapping("ChangeUserName")
    public String changeuserNameinDB(@RequestBody HashMap<String, String> maps,HttpServletRequest req){
        System.out.println("받아온 값 : " + maps.get("name"));
        String sessionId = (String)req.getSession().getAttribute("userId");
        userService.ChangeUserNameTo(sessionId, maps.get("name"));
        return "testpage";
    }

    @PostMapping("ChangeUserPassword")
    public String changeuserPasswordinDB(@RequestBody HashMap<String, String> maps,HttpServletRequest req){

        String sessionId = req.getSession().getAttribute("userId").toString();
        userService.ChangeUserPasswordTo(sessionId, maps.get("password"));
        return "testpage";
    }

    @PostMapping("ChangeUserEmail")
    public String changeuserEmailinDB(@RequestBody HashMap<String, String> maps,HttpServletRequest req){
        String sessionId = req.getSession().getAttribute("userId").toString();
        userService.ChangeUserEmailTo(sessionId, maps.get("email"));
        return "testpage";
    }

    @PostMapping("ChangeUserIntroduce")
    public String changeuserIntroduceinDB(@RequestBody HashMap<String, String> maps,HttpServletRequest req){
        String sessionId = req.getSession().getAttribute("userId").toString();
        userService.ChangeUserIntroduceTo(sessionId, maps.get("introduce"));
        return "testpage";
    }

    @PostMapping("DeleteUser")
    public String deleteuserInfo(@SessionAttribute(name = "userId", required = false) String sessionId){
        userService.DeleteUser(sessionId);
        return "testpage";
    }

    @PostMapping("UserProfilImgUpload")
    public String userProfilImgFileUpload(@SessionAttribute(name = "userId", required = false) String sessionId,
                                          @RequestParam(name = "files") MultipartFile files) throws Exception{
        String Filename = userProfilService.profilImgUpload(sessionId, files);
        userService.userProfilSave(sessionId, Filename);
        return "testpage";
    }


    /**
     *
     * [2025-03-08] 게시판 기능 구현
     */

    // 게시글 추가 기능
    @PostMapping("UserBoardAddPage")
    public String AddNewUserBoard(@SessionAttribute(name="userId", required = false) String sessionId,
                                  @RequestBody UserBoardDTO board){

        System.out.println(board.toString());
        if(Pattern.matches("(?=.*[0-9]).{4}", board.getModify_pwd())) {
            userBoardserv.createNewBoard(board, sessionId);
        }else{
            System.out.println("조건에 맞지 않습니다.");
        }

        return "mainPage";
    }

    // 게시글 수정 기능
    @PostMapping("UserBoardModify/{postId}/modify")
    public String ModifyUserBoard(@PathVariable("postId") int Id,
                                  @SessionAttribute(name="userId", required = false) String sessionId,
                                  @RequestBody UserBoardModifyDTO dto){
        // 개시글의 글 번호를 수정하기 위한 패스워드 확인
        System.out.println("컨트롤러: "+dto.toString());
        if(userBoardserv.modifyPwCheck(Id, dto.getModify_pwd())){
            System.out.println("패스워드 일치, 수정페이지 접근");

            userBoardserv.modifyBoard(Id, sessionId, dto);

        }else{
            System.out.println("패스워드가 일치하지않음. 수정불가");
        }
        return "mainpage";
    }

    @GetMapping("UserBoardModify/{postId}/deletepage")
    public String deleteCheckPage(@SessionAttribute(name="userId", required = false) String sessionId){
        // 게시글 삭제 접근
        return "mainpage";

    }

    @PostMapping("UserBoardModify/{postId}/delete")
    public String DeleteUserBoard(@PathVariable("postId") int Id,
                                  @SessionAttribute(name="userId", required = false) String sessionId,
                                  @RequestBody String modify_pwd) {
        // 개시글을 삭제하기 위한 패스워드 및 사용자 정보 확인
        if(userBoardserv.modifyPwCheck(Id, modify_pwd) && userBoardserv.DeletePwCheck(Id, sessionId)){
            System.out.println("패스워드 일치, 수정페이지 접근");

            userBoardserv.DeleteBoard(Id);

        }else{
            System.out.println("패스워드 혹은 글 작성자가 아님. 수정불가");
        }
        return "mainpage";

    }


}
