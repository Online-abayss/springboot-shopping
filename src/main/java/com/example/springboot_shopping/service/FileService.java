package com.example.springboot_shopping.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
        UUID uuid = UUID.randomUUID();//1
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension;//2
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);//3
        fos.write(fileData);//4
        fos.close();
        return savedFileName;//5
    }

    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath);//6
        if(deleteFile.exists()) {//7
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }

}

/*
1 UUID(Universally Unique Identifier)는 서로 다른 개체들을 구별하기 위해서 이름을 부여할 때 사용합니다.

실제 사용 시 중복될 가능성이 거의 없기 때문에 파일의 이름으로 사용하면 파일명 중복 문제를 해결할 수 있습니다.

2 UUID로 받은 값과 원래 파일의 이름ㅇ의 확장자를 조합해서 저장될 파일 이름을 만듭니다.

3 FileOutPutStream 클래스는 바이트 단위의 출력을 내보는 클래스입니다.

생성자로 파일이 저장될 위치와 파일의 이름을 넘겨 파일에 쓸 파일 출력 스트림을 만듭니다.

4 fileData를 파일 출력 스트림에 입력합니다

5 업로드된 파일의 이름을 반환합니다.

6 파일이 저장된 경로를 이용하여 파일 객체를 생성합니다.

7 해당 파일이 존재하면 파일을 삭제합니다

 */

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
