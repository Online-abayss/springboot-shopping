package com.example.springboot_shopping.service;


import com.example.springboot_shopping.entity.ItemImg;
import com.example.springboot_shopping.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")//1
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(itemImgLocation, oriImgName,
                    itemImgFile.getBytes());//2
            imgUrl = "/images/item/" + imgName;//3
        }

        //상품 이미지 정보 저장
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);//4
        itemImgRepository.save(itemImg);//5
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile ) throws Exception {

        if (!itemImgFile.isEmpty()) {

            ItemImg saveItemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);

            if (!StringUtils.isEmpty(saveItemImg.getImgName())) {

                fileService.deleteFile(itemImgLocation + "/" + saveItemImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item" + imgName;
            saveItemImg.updateItemImg(oriImgName, imgName, imgUrl);

        }
    }
}

/*
1 -> Value 어노테이션을 통해 application.properties 파일에 등록한 itemImgLocation 값을 불러와서 itemImgLocation 변수에 넣어줍니다

2 -> 사용자가 상품을 등록했다면 저장할 경로와 파일의 이름, 파잉을 파일의 바이트 배열을 파일 업로드 파라미터로 uploadFile 메소드를 호출합니다.

호풀 결과 로컬에 저장된 파일의 이름을 imgName 변수에 저장합니다.

3 저장한 상품 이미지를 불러올 경로를 설정합니다. 외부 리소스를 불러오는 urlPatterns로 WebMvcConfig 클래스에 서 "/images/**"를 설정해 주었습니다.

또한 application.properties에서 설장한 uplodaPath 프로퍼티 경로인 c/shop/아래에 item 폴더에 이미지를 저장하므로 이미지를 불러오는 경로로

"/images/item/" 붙여줍니다.

4,5 -> 입력받은 상품 이미지 정보를 저장한다.

oriImgName -> 업로드했던 상품 이미지 파일의 원래 이름

imgName -> 실제 로컬에 저장된 상품 이미지 파일의 이름

imgUrl -> 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로
 */

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
