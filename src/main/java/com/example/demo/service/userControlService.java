package com.example.demo.service;

import com.example.demo.data.dao.UserDataAccessObject;
import com.example.demo.data.dto.LoginPageDTO;
import com.example.demo.data.dto.RegisterPageDTO;
import com.example.demo.data.dto.UserInfoDTO;
import com.example.demo.data.dto.UserProfilDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class userControlService {
    UserDataAccessObject userDAO;


    @Autowired
    public userControlService(UserDataAccessObject userDAO) {
        this.userDAO = userDAO;
    }


    /**
     * [2025-01-25] 회원가입 진행 (폼데이터로 userDTO에 담고, 해당 값을 서비스로
     *
     */
    public boolean userRegistService(RegisterPageDTO userDTO){
        System.out.println("[Service] form 데이터 userDTO 전달받음.");
        System.out.println("[Service] userDTO 값으로 DAO에 전달 (service -> dao");
        return userDAO.userInfoSave(userDTO);
    }



    /**
     * [2025-01-31] 로그인 정보값을 가지고 DB와 비교 후 로그인 진행
     *
     */
    public boolean userTryToLogin(LoginPageDTO userLoginDTO) {
        return userDAO.CompareDataToLogin(userLoginDTO);
    }

    /**
     * [2025-01-31] 사용자 정보 요청시 DTO 객체로 반환
     */
    public UserProfilDTO ReturnUserALLInfoUseID(String userID){
        if(userDAO.checkUserId(userID)){
            return userDAO.ReturnUserALLInfo(userID);
        }else{
            return null;
        }
    }


    /**
     * [2025-02-04] 사용자 프로필 정보 요청시 DTO 객체로 반환
     */
    public UserProfilDTO ReturnUserProfilInfoUseID(String userID){
        if(userDAO.checkUserId(userID)){
            return userDAO.ReturnUserALLInfo(userID);
        }else{
            return null;
        }
    }
    /**
     *  [2025-02-11] 사용자 프로필 정보 수정
     *  닉네임 변경, 비밀번호 변경, 이메일 변경, 프로필 사진 변경, 자기소개 변경 기능 필요
     */
    public void ChangeUserNameTo(String SessionId, String name) {
        userDAO.ChangeUserInDBName(SessionId, name);
    }

    public void ChangeUserPasswordTo(String SessionId, String password) {
        userDAO.ChangeUserInDBPassword(SessionId, password);
    }

    public void ChangeUserEmailTo(String SessionId, String email) {
        userDAO.ChangeUserInDBEmail(SessionId, email);
    }

    public void ChangeUserIntroduceTo(String SessionId, String introduce) {
        userDAO.ChangeUserInDBIntroduce(SessionId, introduce);
    }


    public void DeleteUser(String sessionId) {
        userDAO.DeleteUserInDB(sessionId);
    }

    public void userProfilSave(String sessionId, String savefilename) {
        userDAO.ChangeUserInDBProfilImg(sessionId, savefilename);
    }

    public void ChangeUserRecentConnectionTime(String SessionId) {
        userDAO.ChangeUserRecentConnectionTimeInDB(SessionId);
    }
}
