package com.example.demo.service;

import com.example.demo.data.dao.UserBoardDataAccessObject;
import com.example.demo.data.dto.UserBoardDTO;
import com.example.demo.data.dto.UserBoardModifyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userBoardService {
    UserBoardDataAccessObject boardDAO;

    @Autowired
    public userBoardService(UserBoardDataAccessObject boardDAO) {
        this.boardDAO = boardDAO;
    }

    public void createNewBoard(UserBoardDTO board, String sessionId) {
        boardDAO.createNewBoardInDB(board, sessionId);
    }

    public boolean modifyPwCheck(int id, UserBoardModifyDTO dto) {
        System.out.println("서비스: "+dto.getModify_pwd());
        return boardDAO.modifyBoard(id, dto.getModify_pwd());
    }

    public void modifyBoard(int id, String sessionId, UserBoardModifyDTO dto) {
        boardDAO.modifyBoardTo(id, sessionId, dto);
    }
}
