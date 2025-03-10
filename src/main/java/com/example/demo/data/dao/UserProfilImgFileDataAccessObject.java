package com.example.demo.data.dao;

import com.example.demo.data.entity.userEntity;
import com.example.demo.data.entity.userProfilFileEntity;
import com.example.demo.data.repository.userProfilFileRepository;
import com.example.demo.data.repository.userRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Transactional
public class UserProfilImgFileDataAccessObject {
    userProfilFileRepository fioRepo;
    @Autowired
    public UserProfilImgFileDataAccessObject(userProfilFileRepository fioRepo) {
        this.fioRepo = fioRepo;
    }

    /**
     * [2025-02-13] 파일 업로드 dao 기능 추가
     */

    @Transactional
    public String UploadProfilImg(String sessionId, MultipartFile files){
        String filename = files.getOriginalFilename();
        String fileextension = FilenameUtils.getExtension(filename).toLowerCase();
//        노트북 경로
//        String fileURL = "C:/Beginner-spring/restarts/src/main/resources/ProfilImg/";

//        PC 경로
        String fileURL = "C:/Users/KHB/IdeaProjects/restarts/src/main/resources/ProfilImg/";
        File savefiles;
        String savefileName;
        do {
            savefileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileextension;
            savefiles = new File(fileURL + savefileName);
        } while (savefiles.exists());

        savefiles.getParentFile().mkdirs();
        try {
            files.transferTo(savefiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userProfilFileEntity f = new userProfilFileEntity();
        f.setFilename(savefileName);
        f.setRealname(filename);
        f.setFileURL(fileURL);
        fioRepo.save(f);

//        userEntity userinfoent = userRepo.getReferenceById(sessionId);
//        userEntity ent = new userEntity(userinfoent.getUserID(), userinfoent.getUserName(),
//                userinfoent.getUserEmail(), userinfoent.getUserPassword(),
//                userinfoent.getUserIntroduce(), savefileName,
//                userinfoent.getUserRegistDate(), userinfoent.getUserRecentConnectionDate());
//        userRepo.save(ent);

        return savefileName;
    }
}
