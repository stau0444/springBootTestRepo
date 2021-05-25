package com.example.hello.aync2.controller;

import com.example.hello.aync2.service.AsyncTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/async-test")
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncTestService asyncTestService;

    @GetMapping("")
    public String asyncHello(){
        asyncTestService.hello();
        log.info("method end");
        return "async hello";
    }
}
