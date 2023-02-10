package com.example.springboot_shopping.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)//1
    @JoinColumn(name = "member_id")//2
    private Member member;
}

//1 @OneToOne 어노테이션을 이용해 회원 엔티티와 일대일로 매핑을 합니다.

//2 @JoingColumn 어노테이션을 이용해 매핑할 외래키를 지정합니다.

//name 속성에는 매핑할 외래키의 이름을 설정합니다. @JoingColumn의 name을 명시하지 않으면 JPA가 알아서 ID를 찾지만 컬럼명이 원하는 대로 생성
//되지 않을수 있기 때문에 직접 지정


//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
