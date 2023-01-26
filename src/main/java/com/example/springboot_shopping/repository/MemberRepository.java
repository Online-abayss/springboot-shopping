package com.example.springboot_shopping.repository;

import com.example.springboot_shopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}


/*
1.회원 가입 시 중복된 회원이 있는지 검사 하기 위해서 이메일로 회원을 검사할 수 있도록 쿼리메소드를 작성합니다.

 */

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
