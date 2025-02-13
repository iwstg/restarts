package com.example.demo.data.dao;

import com.example.demo.Configuration.Sha256Encode;
import com.example.demo.data.dto.LoginPageDTO;
import com.example.demo.data.dto.RegisterPageDTO;
import com.example.demo.data.dto.UserInfoDTO;
import com.example.demo.data.dto.UserProfilDTO;
import com.example.demo.data.entity.userEntity;
import com.example.demo.data.entity.userProfilFileEntity;
import com.example.demo.data.repository.userProfilFileRepository;
import com.example.demo.data.repository.userRepository;
import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserDataAccessObject {
    userRepository userRepo;


    @Autowired
    public UserDataAccessObject(userRepository userRepo) {
        this.userRepo = userRepo;
    }

    // 해당 ID 전달받아 DB에 존재하는지 확인
    public boolean checkUserId(String userId) {
        return userRepo.existsById(userId);
    }


    @Transactional
    // 회원가입 객체를 전달받아 그 객체 내용으로 DB에 계정 저장
    public boolean userInfoSave(RegisterPageDTO userDTO) {
        System.out.println("[userDAO] dao에서 DTO 값 전달 받음. 가입 로직 시작");
        userDTO.setUserPassword(Sha256Encode.encrypt(userDTO.getUserPassword()));
        // 중복체크 있으면 false 없으면 엔티티에 넣어 저장 진행
        if (checkUserId(userDTO.getUserId())) {
            System.out.println("[userDAO] 전달받은 DTO에 ID값과 일치하는 것 존재 가입 실패");
            return false;
        } else {
            userEntity ent = new userEntity(userDTO.getUserId(), userDTO.getUserName(), userDTO.getUserEmail(), userDTO.getUserPassword(),"", "defaultimg.png", LocalDateTime.now(), LocalDateTime.now());
            System.out.println("[userDAO] DTO를 entity로 이동및 db 저장");
            userRepo.save(ent);
            return true;
        }
    }


    public boolean CompareDataToLogin(LoginPageDTO userLoginDTO) {
        userEntity ent = userRepo.getReferenceById(userLoginDTO.getUserLoginId());
        userLoginDTO.setUserLoginPassword(Sha256Encode.encrypt(userLoginDTO.getUserLoginPassword()));
        if (userLoginDTO.getUserLoginPassword().equals(ent.getUserPassword())) {
            System.out.println("[userDAO] 전달받은 DTO와 일치하는 패스워드임");
            return true;
        } else {
            System.out.println("[userDAO] 전달받은 DTO와 일치하지 않는 패스워드임");
            return false;
        }

    }

    // UserInfoDTO 로 리턴
    public UserInfoDTO ReturnUserInfo(String userID) {
        userEntity ent = userRepo.getReferenceById(userID);
        return new UserInfoDTO(ent.getUserID(), ent.getUserName(), ent.getUserEmail());
    }

    // UserProfilDTO 로 리턴
    public UserProfilDTO ReturnUserProfilInfo(String userID) {
        userEntity ent = userRepo.getReferenceById(userID);
        return new UserProfilDTO(ent.getUserID(), ent.getUserName(), ent.getUserEmail(), ent.getUserIntroduce(), ent.getUserProfilImg());
    }

    /**
     *  [2025-02-11] 사용자 프로필 정보 수정
     *  닉네임 변경, 비밀번호 변경, 이메일 변경, 프로필 사진 변경, 자기소개 변경 기능 필요
     */
    @Transactional
    public void ChangeUserInDBName(String SessionId, String name) {
        userEntity userinfoent = userRepo.getReferenceById(SessionId);
        System.out.println("정보값: " + userinfoent.toString());
        userEntity ent = new userEntity(userinfoent.getUserID(), name,
                           userinfoent.getUserEmail(), userinfoent.getUserPassword(),
                            userinfoent.getUserIntroduce(), userinfoent.getUserProfilImg(),
                            userinfoent.getUserRegistDate(), userinfoent.getUserRecentConnectionDate());
        userRepo.save(ent);
    }

    @Transactional
    public void ChangeUserInDBPassword(String SessionId, String password) {
        userEntity userinfoent = userRepo.getReferenceById(SessionId);

        userEntity ent = new userEntity(userinfoent.getUserID(), userinfoent.getUserName(),
                userinfoent.getUserEmail(), Sha256Encode.encrypt(password),
                userinfoent.getUserIntroduce(), userinfoent.getUserProfilImg(),
                userinfoent.getUserRegistDate(), userinfoent.getUserRecentConnectionDate());
        userRepo.save(ent);
    }

    @Transactional
    public void ChangeUserInDBEmail(String SessionId, String email) {
        userEntity userinfoent = userRepo.getReferenceById(SessionId);
        userEntity ent = new userEntity(userinfoent.getUserID(), userinfoent.getUserName(),
                email, userinfoent.getUserPassword(),
                userinfoent.getUserIntroduce(), userinfoent.getUserProfilImg(),
                userinfoent.getUserRegistDate(), userinfoent.getUserRecentConnectionDate());
        userRepo.save(ent);
    }

    @Transactional
    public void ChangeUserInDBIntroduce(String SessionId, String introduce) {
        userEntity userinfoent = userRepo.getReferenceById(SessionId);
        userEntity ent = new userEntity(userinfoent.getUserID(), userinfoent.getUserName(),
                userinfoent.getUserEmail(), userinfoent.getUserPassword(),
                introduce, userinfoent.getUserProfilImg(),
                userinfoent.getUserRegistDate(), userinfoent.getUserRecentConnectionDate());
        userRepo.save(ent);
    }

    @Transactional
    public void DeleteUserInDB(String sessionId) {
        userRepo.deleteById(sessionId);
    }
}
