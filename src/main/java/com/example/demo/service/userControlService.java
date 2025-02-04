package com.example.demo.service;

import com.example.demo.data.dao.UserDataAccessObject;
import com.example.demo.data.dto.LoginPageDTO;
import com.example.demo.data.dto.RegisterPageDTO;
import com.example.demo.data.dto.UserInfoDTO;
import com.example.demo.data.dto.UserProfilDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
    public UserInfoDTO ReturnUserInfoUseID(String userID){
        if(userDAO.checkUserId(userID)){
            return userDAO.ReturnUserInfo(userID);
        }else{
            return null;
        }
    }


    /**
     * [2025-02-04] 사용자 프로필 정보 요청시 DTO 객체로 반환
     */
    public UserProfilDTO ReturnUserProfilInfoUseID(String userID){
        if(userDAO.checkUserId(userID)){
            return userDAO.ReturnUserProfilInfo(userID);
        }else{
            return null;
        }
    }
}
