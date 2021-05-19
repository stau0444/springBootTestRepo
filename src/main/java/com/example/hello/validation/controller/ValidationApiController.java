package com.example.hello.validation.controller;

import com.example.hello.validation.dto.User;
import com.example.hello.validation.dto.User2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ValidationApiController {


    //예전방식으로 vaildation 한다면 아래와 같다.
    @PostMapping("/user-validation")
    public ResponseEntity<User> user(@RequestBody User user){
        System.out.println(user);
        if(user.getAge()>=90) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        return ResponseEntity.ok(user);
    }

    //Spring annotation을 활용한 validation
    @PostMapping("/user-validation2")
    //파라미터 오브젝트에 validation 하는 annotation이 있다면
    //@Valid를 달아줘야한다.
    //컨트롤러에 들어와서 파라미터에 @Valid가 있다면 검증을 들어간다.
    public ResponseEntity user2(@Valid @RequestBody User2 user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();
                System.out.println(message);

                sb.append("field:"+field.getField());
                sb.append("message:" + message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        //logic

        System.out.println(user);

        return ResponseEntity.ok(user);
    }

}
