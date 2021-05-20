package com.example.hello.filterTest.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;

//필터를 특정 url 에만 적용하고싶으면
//@WebFilter에 원하는 url 지정
//애플리케이션 메인메서드에 @ServletComponentScan 붙힌다.
@WebFilter(urlPatterns = "/api/filter/*")
@Slf4j
public class GlobalFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //필터에서는 request , response 객체를 변경할 수 있다.



        //전 처리

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);



        // 필터단에서 한번 바디를 읽었기 때문에 dispatcherServlet을 통해 Controller단으로 요청이
        // 넘어가면 Obejectmapper가  해당 바디를  파라미터로 지정된 오브젝트에 매칭시키기 위해
        // 요청 바디를 다시 읽게된다. 자바에서 스트림은 커서단위로 읽히고 한번읽힌 스트림은 다시 읽을 수 없다 .
        // 이때 에러가 발생한다
        // 이를 해결하기 위해 ContentCachingRequestWrapper클래스를 사용한다
        // 해당 타입으로 요청을 받으면
        // 요청 받은 것을 캐싱하여 다음번에 읽을 때는 캐싱된 내용을 리턴해준다.


        chain.doFilter(httpServletRequest,httpServletResponse);


        //후 처리

        //req
        String requestURI = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url : {}  ,request body:{}",requestURI,reqContent);

        //resp
        int httpStatus = httpServletResponse.getStatus();
        String respContent = new String(httpServletResponse.getContentAsByteArray());
        log.info("response status:{} , responseBody:{}",httpStatus,respContent);

        //
        httpServletResponse.copyBodyToResponse();








    }
}
