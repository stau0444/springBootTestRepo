package com.example.hello.exception.controller;

import com.example.hello.exception.dto.User;
import com.example.hello.interceptorWithException.exception.NoAuthException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")
@Validated
public class ExceptionController {

    @GetMapping("/test")
    public void AuthTest(){
        throw new NoAuthException("NoAuth 터짐");
    }

    @GetMapping
    //@RequestParam의 required 옵션이 false면 null이어도 동작은 하도록한다.
    public User get(@Size(min = 2)
                    @RequestParam String name,
                    @NotNull
                    @Min(1)
                    @RequestParam Integer age){
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

    @ExceptionHandler(value = JsonMappingException.class)
    public ResponseEntity JsonMappingException(JsonMappingException e){
        System.out.println("JsonMappingException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
