package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    //delete -> 리소스 삭제 항상 200 ok  멱등성
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId , @RequestParam String account){
        System.out.println(userId);
        System.out.println(account);
    }
}
