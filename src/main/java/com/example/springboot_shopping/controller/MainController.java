package com.example.springboot_shopping.controller;


import com.example.springboot_shopping.dto.ItemSearchDto;
import com.example.springboot_shopping.dto.MainItemDto;
import com.example.springboot_shopping.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping(value = "/") // 메인페이지로 갈 수 있도록 하는 소스
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "main";
    }
}


//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
