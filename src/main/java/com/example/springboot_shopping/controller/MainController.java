package com.example.springboot_shopping.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping(value = "/") // 메인페이지로 갈 수 있도록 하는 소스
    public String main() {
        return "main";
    }
}
