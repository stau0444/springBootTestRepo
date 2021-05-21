package com.example.hello.interceptorWithException.intetceptor;

import com.example.hello.interceptorWithException.annotation.Auth1;
import com.example.hello.interceptorWithException.exception.NoAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

@Slf4j
@Component
public class AuthInterceptor1  implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("인터셉터들어옴");
        //String url = request.getRequestURI();
        String queryString = request.getQueryString();
        log.info("queryString:{}",queryString);
        //boolean hasAnnotation = checkAnnotation(handler, Auth1.class);
        //log.info("hasAnnotation:{}",hasAnnotation);


        //if(hasAnnotation){
            if(!queryString.equals("name=ugo")){
                throw new NoAuthException("ugo가 아님:");
            }
        //}
        //return true;
        return false;
    }


    private boolean checkAnnotation(Object handler, Class clazz){

        if(handler instanceof ResourceHttpRequestHandler){
            //인터셉터 무조건 통과
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;


        //Auth1 어노테이션이 붙어있는가를 확인한다.
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            return  true;
        }
        return false;
    }

}
