package com.example.springboot_shopping.repository;


import com.example.springboot_shopping.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {//8
    List<Item> findByItemNm(String itemNm);//1

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);//2

    List<Item> findByPriceLessThan(Integer price);//3

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);//4

    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")//5
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);//6

    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)//7
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}



/*
1.상품명 데이터를 조회하기 위해서 By뒤에 필드명인 ItemNm을 메소드의 이름에 붙여준다.

쿼리 메소드는 기본적으로 find +엔티티이름 +By +변수이름 이지만 엔티티 이름은 생략 가능

매개변수로 검색할때 상품명  변수를 넘겨준다.

2.상품을 상품명과 상세 설명을 OR 조건을 이용하여 조회하는 쿼리 메소드입니다.

3.

4.

5.

6.

7.

 */


//참고서적: 백견이불여일타 스프링부트와 쇼핑몰 with JPA
