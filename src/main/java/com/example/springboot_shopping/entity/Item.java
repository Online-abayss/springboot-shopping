package com.example.springboot_shopping.entity;

import com.example.springboot_shopping.constant.ItemSellStatus;
import com.example.springboot_shopping.dto.ItemFormDto;
import com.example.springboot_shopping.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//상품코드

    @Column(nullable = false, length = 50)
    private String itemNm;//상품이름

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto) {

        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber) {

        int restStock = this.stockNumber - stockNumber;

        if(restStock<0) {

            throw new OutOfStockException("상품의 재고가 부족 합니다. " +
                    "(현재 재고 수량: " + this.stockNumber + ")");
        }

        this.stockNumber = restStock;
    }
}

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
