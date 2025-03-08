package com.example.demo.data.dao;

import com.example.demo.data.dto.UserBoardDTO;
import com.example.demo.data.dto.UserBoardModifyDTO;
import com.example.demo.data.entity.userBoardEntity;
import com.example.demo.data.repository.userBoardRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.LocalTime.now;

@Service
@Transactional
public class UserBoardDataAccessObject {
    userBoardRepository boardRepo;

    @Autowired
    public UserBoardDataAccessObject(userBoardRepository boardRepo) {
        this.boardRepo = boardRepo;
    }


    // 게시판 생성기능
    public void createNewBoardInDB(UserBoardDTO board, String sessionId) {
        userBoardEntity ent = new userBoardEntity(0,board.getTitle(), board.getContents(), sessionId, sessionId, board.getModify_pwd(), LocalDateTime.now(), LocalDateTime.now());
        boardRepo.save(ent);
    }


    // 게시판 수정 기능 ( 해당 번호 게시글의 패스워드 체크)
    public boolean modifyBoard(int id, String modifyPwd) {
        System.out.println("전달받은 패스워드: " + modifyPwd);
        userBoardEntity ent = boardRepo.findByboardnum(id);
        System.out.println(ent.toString());
        System.out.println(ent.getModify_pwd());
        return ent.getModify_pwd().equals(modifyPwd);
    }


    public void modifyBoardTo(int id, String sessionId, UserBoardModifyDTO dto) {
        /**
         *  userBoardRepository에 정의된 쿼리로 업데이트 진행
         */
        boardRepo.UpdateBoard(dto.getTitle(), dto.getContents(), sessionId, id, LocalDateTime.now());
    }
}
