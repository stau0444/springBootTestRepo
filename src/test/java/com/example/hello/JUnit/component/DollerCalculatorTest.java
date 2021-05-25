package com.example.hello.JUnit.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

//Component로 등록된 빈을 사용할 수 있다 .
@SpringBootTest
class DollerCalculatorTest {

    @MockBean
    public MarketApi marketApi;

    @Autowired
    public DollerCalculator dollerCalculator;

    @Test
    public void dollerCalcTest(){

        Mockito.when(marketApi.connect()).thenReturn(3000);

        dollerCalculator.init();

        int sum = dollerCalculator.sum(10,10);
        int minus = dollerCalculator.minus(10,10);

        Assertions.assertEquals(60000,sum);
        Assertions.assertEquals(0, minus);
    }
}