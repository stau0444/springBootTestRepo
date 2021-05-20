package com.example.hello.interceptorTest.handler;

import com.example.hello.interceptorTest.exception.AuthException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity authException(AuthException ae){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ae.getMessage());
    }
}
