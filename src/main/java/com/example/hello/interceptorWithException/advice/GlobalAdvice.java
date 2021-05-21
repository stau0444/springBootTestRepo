package com.example.hello.interceptorWithException.advice;

import com.example.hello.interceptorWithException.exception.NoAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class GlobalAdvice {

    @ExceptionHandler(NoAuthException.class)
    public ResponseEntity NoAuthExceptionHandler(NoAuthException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()+HttpStatus.UNAUTHORIZED);
    }
}
