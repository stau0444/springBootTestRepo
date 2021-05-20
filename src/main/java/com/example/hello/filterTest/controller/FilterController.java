package com.example.hello.filterTest.controller;

import com.example.hello.filterTest.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/filter")
public class FilterController {

    @PostMapping("")
    public User user(@RequestBody User user, HttpServletRequest request){
        String requestURI = request.getRequestURI();

        user.setName("황경욱");
        log.info("User: {}",user);
        return user;
    }
}
