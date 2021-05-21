package com.example.hello.interceptorWithException.exception;

public class NoAuthException extends RuntimeException {

    private String message;

    public NoAuthException(String message) {
        super(message);
        this.message = message;
    }
}
