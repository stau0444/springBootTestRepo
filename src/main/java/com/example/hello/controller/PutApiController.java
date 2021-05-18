package com.example.hello.controller;

import com.example.hello.Dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto, @PathVariable Long userId){
        System.out.println("user id=  " + userId);
        System.out.println(putRequestDto.toString());

        //스프링에서 ObjectMapper를 통해 해당 오브젝트를 Json 형식으로 변환하여 리턴한다.
        return putRequestDto;
    }

}
