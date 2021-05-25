package com.example.hello.JUnit.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DollerCalculator implements ICalculator {

    private int price = 1;
    //외부 api에 conncet 하여 환율을 가져오는 API
    public final MarketApi marketApi;

    //MarketApi를 통해 가져온 환율을 price에 초기화 해주는 메서드
    @Override
    public  void init(){
        this.price = marketApi.connect();
    }


    @Override
    public int sum(int x, int y) {
        return price*(x+y);
    }

    @Override
    public int minus(int x, int y) {
        return price*(x-y);
    }
}
