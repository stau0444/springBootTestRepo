package com.example.hello.controller;

import com.example.hello.Dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String,Object> requestData){
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });
    }

    @PostMapping("/post-dto")
    public void post(@RequestBody PostRequestDto postRequestDto) {
        System.out.println(postRequestDto.toString());
    }

    //자바에서는 카멜케이스 제이슨에서는 스네이크 케이스일 경우
    //자바에서 변수선언 디폴트가 카멜케이스이기 때문에 Json의 키값이
    //snake case 일 경우  스프링이 매칭시키지 못한다.
    //해당 Dto 필드에 @JsonProperty 어노테이션으로 Json 키값 지정이 가능하다.


    @PostMapping("/snake-case")
    public void snakeCase(@RequestBody PostRequestDto postRequestDto) {
        System.out.println(postRequestDto.toString());
    }

}
