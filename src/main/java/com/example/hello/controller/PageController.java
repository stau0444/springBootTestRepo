package com.example.hello.controller;


import com.example.hello.Dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        //resource 하위에 static에서 부터 찾는다.
        return "main.html";
    }

    //페이지 내려줄때는  Json을 어떻게 넘겨주나

    //ResponseEntity

    @ResponseBody
    @GetMapping("/user")
    public User user(){
        //var 예약어로 타입추론이 가능하다.
        var user = new User();
        user.setName("ugo");
        user.setAddress("yang ju");
        return user;
    }


}
