package com.example.springboot_shopping.Service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileDate) throws  Exception {

        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl); // 바이트 단위의 출력을 보내는 클래스
        fos.write(fileDate);
        fos.close();
        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception {

        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {

            deleteFile.delete();
            log.info("파일 삭제");
        } else {
            log.info("파일 無");
        }



    }
}
