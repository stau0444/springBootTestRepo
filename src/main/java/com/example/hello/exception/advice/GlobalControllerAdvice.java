package com.example.hello.exception.advice;

import com.example.hello.exception.controller.ExceptionController;
import com.example.hello.exception.dto.Error;
import com.example.hello.exception.dto.ErrorResponse;
import com.example.hello.interceptorWithException.exception.NoAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@RestControllerAdvice(basePackageClasses = ExceptionController.class)  //RestAPI 컨트롤러용 advice
//@ViewResolverAdvice MVC 형식일때의 advice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("GLOBAL Exception");

    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e,HttpServletRequest request){
        //error 담을 리스트
        List<Error>  errors = new ArrayList<>();

        //에러에서 BindingResult를 가져와
        BindingResult bindingResult = e.getBindingResult();
        //해당 BindingResult을 FieldE
        bindingResult.getAllErrors().forEach(error ->{
            FieldError field = (FieldError) error;
            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String invalidValue = field.getRejectedValue().toString();

            Error errorMessage = new Error();
            errorMessage.setMessage(message);
            errorMessage.setField(fieldName);
            errorMessage.setInvalidValue(invalidValue);

            errors.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errors);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 현재 request를 매개변수로 받아올 수 있다.
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e, HttpServletRequest request){

        //
        List<Error>  errors = new ArrayList<>();

        e.getConstraintViolations().forEach(error -> {

            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(),false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String field = list.get(list.size() -1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            Error errorMessage = new Error();
            errorMessage.setMessage(message);
            errorMessage.setField(field);
            errorMessage.setInvalidValue(invalidValue);

            errors.add(errorMessage);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errors);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }



    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity MissingServletRequestParameterException(MissingServletRequestParameterException e , HttpServletRequest request){

        List<Error>  errors = new ArrayList<>();

        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidValue = e.getMessage();

        Error errorMessage = new Error();
        errorMessage.setMessage(fieldName);
        errorMessage.setField(fieldType);
        errorMessage.setInvalidValue(invalidValue);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errors);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
