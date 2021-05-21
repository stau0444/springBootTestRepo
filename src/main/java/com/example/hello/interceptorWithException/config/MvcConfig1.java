package com.example.hello.interceptorWithException.config;


import com.example.hello.interceptorWithException.intetceptor.AuthInterceptor1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig1 implements WebMvcConfigurer {

    private final AuthInterceptor1 authInterceptor1;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 는 파라미터로 들어가는 경로만 Interceptor를 타게 한다.
        registry.addInterceptor(authInterceptor1).addPathPatterns("/api/private1");
        //인터셉터가 여러개라면 순서대로 작동한다.
    }


}
