package com.example.springboot_shopping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {

    private Long id;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainItemDto(Long id, String itemNm, String itemdetail, String imgUrl, Integer price) {

        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemdetail;
        this.imgUrl = imgUrl;
        this.price = price;


    }
    // @QueryProjection 사용시 QDto가 필요하다. 이 어노테이션은 querydsl로 조회시 MainItemDto 객체로 바로 받아오도록 설정.

}
