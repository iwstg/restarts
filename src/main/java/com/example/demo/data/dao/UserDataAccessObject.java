package com.example.demo.data.dao;

import com.example.demo.data.dto.LoginPageDTO;
import com.example.demo.data.dto.RegisterPageDTO;
import com.example.demo.data.entity.userEntity;
import com.example.demo.data.repository.userRepository;
import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDataAccessObject {
    userRepository userRepo;

    @Autowired
    public UserDataAccessObject(userRepository userRepo){
        this.userRepo = userRepo;
    }

    // 해당 ID 전달받아 DB에 존재하는지 확인
    public boolean checkUserId(String userId){
        return userRepo.existsById(userId);
    }


    // 회원가입 객체를 전달받아 그 객체 내용으로 DB에 계정 저장
    public boolean userInfoSave(RegisterPageDTO userDTO){
        System.out.println("[userDAO] dao에서 DTO 값 전달 받음. 가입 로직 시작");
        // 중복체크 있으면 false 없으면 엔티티에 넣어 저장 진행
        if(checkUserId(userDTO.getUserId())) {
            System.out.println("[userDAO] 전달받은 DTO에 ID값과 일치하는 것 존재 가입 실패");
            return false;
        }else{
            userEntity ent = new userEntity(userDTO.getUserId(), userDTO.getUserName(), userDTO.getUserEmail(), userDTO.getUserPassword());
            System.out.println("[userDAO] DTO를 entity로 이동및 db 저장");
            userRepo.save(ent);
            return true;
        }
    }


    public boolean CompareDataToLogin(LoginPageDTO userLoginDTO) {
       userEntity ent = userRepo.getReferenceById(userLoginDTO.getUserLoginId());
       if(userLoginDTO.getUserLoginPassword().equals(ent.getUserPassword())){
           System.out.println("[userDAO] 전달받은 DTO와 일치하는 패스워드임");
           return true;
       }else{
           System.out.println("[userDAO] 전달받은 DTO와 일치하지 않는 패스워드임");
           return false;
       }

    }
}
