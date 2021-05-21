package com.example.hello.interceptorWithException.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public1")
public class PublicController1 {

    @GetMapping("")
    public String publicHello(){
        return "public hello";
    }
}
