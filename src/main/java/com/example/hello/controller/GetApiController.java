package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetApiController {

    @GetMapping("/gethello")
    public String  getHello(){
        return "get hello";
    }
    @RequestMapping(path = "/hi" , method= RequestMethod.GET)
    public String hi(){
        return  "hi";
    }

    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable("name") String pathName){
        System.out.println(pathName);
        return "이름은"+pathName;
    }

    @GetMapping("/query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam Long id
    ){
        System.out.println(name);
        System.out.println(id);

        return name + " " + id ;
    }


    //Dto로 받을때 @RequestParam 붙히지 않는다
    //파라미터를 객체로 받을 경우 스프링이 쿼리스트링을
    //읽어 해당 객체의 필드와 매칭을 시켜준다
    //쿼리 스트링에 
    @GetMapping("/query-param03")
    public String queryParam03(UserDto userDto){
        System.out.println(userDto.getName());
        System.out.println(userDto.getEmail());
        System.out.println(userDto.getAge());
        return userDto.toString();
    }
}
