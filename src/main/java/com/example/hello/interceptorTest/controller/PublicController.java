package com.example.hello.interceptorTest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//누구나 사용할 수 있는 openAPI 컨트롤러
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/hello")
    public String hello(){
        return "public hello";
    }
}
