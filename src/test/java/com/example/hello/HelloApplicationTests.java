package com.example.hello;

import com.example.hello.Dto.ObjectMapperUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("hello");

        //ObjectMapper 동작 방식 테스트
        //Text JSON-> Object
        //Object -> Text JSON
        //위의 역할을 한다.

        var objectMapper = new ObjectMapper();

        //1.object를 text로 변환
        //object->text 로 변환시 getter 메소드를 활용한다.
        var user = new ObjectMapperUser("ugo",10,"01004040404");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        //2.text->object
        //text -> objectMapper로 바뀔 경우 default 생성자가 필요하다.
        var objectUser = objectMapper.readValue(text,ObjectMapperUser.class);
        System.out.println(objectUser);


        //ObjectMapper를 통해 Jason node에 접근하는 방법


    }
}
