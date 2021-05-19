package com.example.hello.exception.controller;

import com.example.hello.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ExceptionController {

    @GetMapping
    //@RequestParam의 required 옵션이 false면 null이어도 동작은 하도록한다.
    public User get(@RequestParam(required = false) String name,
                    @RequestParam(required = false) Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);

        // age가 null 이면 nullPointerException이 발생한다
        int a = 10 + age;

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){

        System.out.println(user);
        return user;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("controller handler");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("controller handler");
    }
}
