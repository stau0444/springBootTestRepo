package com.example.hello.interceptorTest.controller;

import com.example.hello.interceptorTest.annotation.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//인증된 사용자만 접근할 수 있는 컨트롤러
@RestController
@RequestMapping("/api/private")
@Auth
public class PrivateController {

    @GetMapping("/hello")
    public String hello(){
        return  "public hello";
    }

}
