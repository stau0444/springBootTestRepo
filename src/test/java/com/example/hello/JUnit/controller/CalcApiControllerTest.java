package com.example.hello.JUnit.controller;

import com.example.hello.JUnit.component.Calculator;
import com.example.hello.JUnit.component.DollerCalculator;
import com.example.hello.JUnit.component.MarketApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

//Web 테스트일 경우 아래 어노테이션 사용 옵션으로는 특정 모듈을 지정할 수 있다 .
//SpringbootTest는 모든 빈이 다 등록이 되기 떄문에 자원소비가 좀 더 많다 .
@WebMvcTest(CalcApiController.class)
@AutoConfigureWebMvc
@Import({Calculator.class , DollerCalculator.class})
public class CalcApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public  void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }


    @Test
    public void  sumTest() throws Exception {
        //http://localhost:8080/api/sum

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/calc/sum")
                .queryParam("x","10")
                .queryParam("y","10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("23333")
        ).andDo(MockMvcResultHandlers.print());
    }
}