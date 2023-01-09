package com.example.springboot_shopping;

import org.aspectj.weaver.World;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShoppingApplication.class, args);
    }

    @GetMapping(value = "/")
    public String HelloWorld(){
        return "Hellow";
    }

}
