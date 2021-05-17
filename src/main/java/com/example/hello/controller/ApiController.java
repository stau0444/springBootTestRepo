package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") //URI를 지정하는 어노테이션
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
