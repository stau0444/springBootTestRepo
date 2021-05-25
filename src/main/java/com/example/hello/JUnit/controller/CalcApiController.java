package com.example.hello.JUnit.controller;


import com.example.hello.JUnit.component.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calc")
@RequiredArgsConstructor
public class CalcApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){

        return calculator.sum(x,y);
    }

    @GetMapping("/minus")
    public int minus(@RequestParam int x, @RequestParam int y){
        return calculator.minus(x,y);
    }
}
