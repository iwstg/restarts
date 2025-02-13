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

@Service
@Transactional
public class UserProfilImgFileDataAccessObject {
    userProfilFileRepository fioRepo;
    @Autowired
    public UserProfilImgFileDataAccessObject(userProfilFileRepository fioRepo) {
        this.fioRepo = fioRepo;
    }

    userRepository userRepo;
    /**
     * [2025-02-13] 파일 업로드 dao 기능 추가
     */

    @Transactional
    public void ProfilImgUpload(String sessionId, MultipartFile files){
        String filename = files.getOriginalFilename();
        String fileextension = FilenameUtils.getExtension(filename).toLowerCase();
        String fileURL = "C:\\Beginner-spring\\restarts\\src\\main\\resources\\ProfilImg\\";
        File savefiles;
        String savefileName;
        do {
            savefileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileextension;
            savefiles = new File(fileURL + savefileName);
        } while (savefiles.exists());

        userProfilFileEntity f = new userProfilFileEntity();
        f.setFilename(savefileName);
        f.setRealname(filename);
        f.setFileURL(fileURL);
        fioRepo.save(f);

        userEntity userinfoent = userRepo.getReferenceById(sessionId);
        userEntity ent = new userEntity(userinfoent.getUserID(), userinfoent.getUserName(),
                userinfoent.getUserEmail(), userinfoent.getUserPassword(),
                userinfoent.getUserIntroduce(), savefileName,
                userinfoent.getUserRegistDate(), userinfoent.getUserRecentConnectionDate());
        userRepo.save(ent);
    }


}
