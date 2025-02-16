package com.example.demo.controller;

import com.example.demo.data.dao.UserProfilImgFileDataAccessObject;
import com.example.demo.service.userProfilImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserProfilFileUploadController {

    userProfilImgService userserv;

    @Autowired
    public UserProfilFileUploadController(userProfilImgService userserv){
        this.userserv = userserv;
    }


    @GetMapping("testpageRo")
    public String testpageanotherservice(){
        return "testpage";
    }

    /**
     * [2025-02-13] 파일 업로드 컨트롤러 추가
     */
    @PostMapping("UserProfilImgUpload")
    public String userProfilImgFileUpload(@SessionAttribute(name = "userId", required = false) String sessionId,
                                          @RequestParam(name = "files") MultipartFile files) throws Exception{
        userserv.profilImgUpload(sessionId, files);
        return "testpage";
    }
}
