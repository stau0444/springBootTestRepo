package com.example.hello.controller;

import com.example.hello.Dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //URI를 지정하는 어노테이션
public class ApiController {


    //Text
    @GetMapping("/text")
    public String hello(@RequestParam String account){
        return account;
    }

    // JSON
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    }

    @PutMapping("/put-response")
    public ResponseEntity<User> User_put(@RequestBody User user){
        //http status 를 지정해서 내려준다.
        //response body에 값을 넣을 수도 있다.
        //응답에 대해 커스터마이징이 필요할 때 ResponseEntity를 사용
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
