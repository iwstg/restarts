package com.example.demo.service;

import com.example.demo.data.dao.UserProfilImgFileDataAccessObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class userProfilImgService {
    UserProfilImgFileDataAccessObject saveDAO;

    @Autowired
    public userProfilImgService(UserProfilImgFileDataAccessObject usersaveDAO){
        this.saveDAO = usersaveDAO;
    }


    public void profilImgUpload(String sessionId, MultipartFile files) {
        saveDAO.UploadProfilImg(sessionId, files);

    }
}
