package com.example.springboot_shopping.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExcontroller {

    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model){

        model.addAttribute("data", "타임리프 예제");
        return "thymeleafEx/thymeleafEx01";
    }
}
