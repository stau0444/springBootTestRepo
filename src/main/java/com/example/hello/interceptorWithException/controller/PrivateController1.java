package com.example.hello.interceptorWithException.controller;

import com.example.hello.interceptorWithException.annotation.Auth1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private1")
@Auth1
public class PrivateController1 {

    @GetMapping("")
    public String privateHello(){
        return "private Hello";
    }

}
