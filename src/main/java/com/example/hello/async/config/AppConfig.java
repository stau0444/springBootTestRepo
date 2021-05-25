package com.example.hello.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AppConfig {

    //CompletableFuture는 뭔가
    //ThreadPoolTaskExecutor는 뭔가
    //Spring web-flux는 뭔가.

    @Bean("async-thread")
    public Executor asyncThread(){

        //스레드풀 작동 방식
        //코어에 먼저 10개가 찬다 - 큐로 10개가넘어간다 - 코어가 10개 늘어난다 - 큐로 10개가 간다 - 총 100개가 되면 터진다.

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        //쓰레드 풀 사이즈
        threadPoolExecutor.setMaxPoolSize(100);
        //쓰레드 코어사이즈
        threadPoolExecutor.setCorePoolSize(10);
        //
        threadPoolExecutor.setQueueCapacity(10);

        threadPoolExecutor.setThreadNamePrefix("Aysnc-");

        return threadPoolExecutor;
    };

}
