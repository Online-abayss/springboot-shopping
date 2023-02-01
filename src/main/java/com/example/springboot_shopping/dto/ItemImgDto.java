package com.example.springboot_shopping.dto;


import com.example.springboot_shopping.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {

        return modelMapper.map(itemImg, ItemImgDto.class);
    }


}


// modelmapper 라이브러리  : 서로 다른 클래스의 값을 필드의 이름과 자료형이 같을경우 getter, setter를 통해 값을 복사해서 객체 봔한.
