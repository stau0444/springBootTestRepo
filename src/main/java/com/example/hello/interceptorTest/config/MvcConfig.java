package com.example.hello.interceptorTest.config;

import com.example.hello.interceptorTest.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // '/api/private' 이하의 모든 주소에서 해당 인터셉터를 타게 한다.
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
    }
}
