package com.example.springboot_shopping.controller;

import com.example.springboot_shopping.dto.OrderDto;
import com.example.springboot_shopping.dto.OrderHistDto;
import com.example.springboot_shopping.service.OrderService;
import lombok.RequiredArgsConstructor;

import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity<?> order(@RequestBody @Valid OrderDto orderDto,
                                              BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long orderId;

        try {
            orderId = orderService.order(orderDto, email);
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping(value = {"/orders" , "/orders/{page}"})
    public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        Page<OrderHistDto> orderHistDtoList = orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", orderHistDtoList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);

        return "order/orderHist";

    }

    @PostMapping("/order/{orderId}/cancel")
    public @ResponseBody ResponseEntity<?> cancelOrder(@PathVariable("orderId") Long orederId, Principal principal) {

        if(!orderService.validateOrder(orederId, principal.getName())) {

            return new ResponseEntity<>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);

        }

        orderService.cancelOrder(orederId);
        return new ResponseEntity<>(orederId, HttpStatus.OK);

    }

}

// @ 현재 로그인 정보를 얻기 위해 @Controller로 선언된 클래스에서 메소드 인자로 pricipal 객체를 넘겨 줄 경우 직접 접근 가능.
// 어디를 찾아봐도 pricipal을 직접 언급하거나 쓴 경우가 없지만, Authentication 보다 상위 클래스가 pricipal이다.
// https://codevang.tistory.com/273
