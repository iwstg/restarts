package com.example.demo.service;

import com.example.demo.data.dao.UserProfilImgFileDataAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userProfilImgService {
    UserProfilImgFileDataAccessObject saveDAO;

    @Autowired
    public userProfilImgService(UserProfilImgFileDataAccessObject usersaveDAO){
        this.saveDAO = usersaveDAO;
    }




}
