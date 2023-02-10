package com.example.springboot_shopping.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/") // 메인페이지로 갈 수 있도록 하는 소스
    public String main() {
        return "main";
    }
}


//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
