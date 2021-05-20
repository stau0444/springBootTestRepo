package com.example.hello.interceptorTest.interceptor;

import com.example.hello.interceptorTest.annotation.Auth;
import com.example.hello.interceptorTest.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //필터 -> dispatcherServlet - > Interceptor 순으로 내려오기때문에
        //필터에서 ContentCachingRequestWrapper 타입으로 변환했다면
        //인터셉터에서 requset 를 ContentCachingRequestWrapper로 형변환하여 받을 수 있다 .

        //uri를 인터셉터에서 가져왔다.
        String url =  request.getRequestURI();

        URI uri  = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}" , url);

        //어노테이션 체크
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("hasAnnotation:{}",hasAnnotation);

        //서버가 모두 public으로 동작하지만
        //auth 권한을 가진 요청에 대해서는 세션, 쿠키 등을 확인하겠다

        if(hasAnnotation){
            //권한체크
            String query = uri.getQuery();
            log.info("query:{}",query);
            if(query.equals("name=ugo")){
                return true;
            }
            throw new AuthException("ugo가 아님");
        }


        //인터셉에서 중요한 것은 handler다
        //handler는 데이터 바인딩 , validation , 컨트롤러 핸들러 맵핑 등 정보가 드렁있다 .

        return true;
    }

    //Class 클래스는 자바어플리케이션에서 돌아가는 클래스, 인터페이스 ,어노테이션에 대한 정보를 가지고 있다.
    private boolean checkAnnotation(Object handler, Class clazz){

        //요청하는 것이 resource javascript ,html 일 경우 무조건 통과
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String name = method.getName();
        log.info("name:{}", name);
        //Auth annotation 을 가지고 있는지 없는지 확인
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            return true;
        }

        return false;
    }
}
