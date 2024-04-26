package com.examplegracefullshutdown.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    @Qualifier("TestTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    public void asyncProcess() {

//        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("iterasi ke " + i);
            }
//        },taskExecutor);
    }

    @GetMapping("/hello")
    public String helloWorld()  {
        asyncProcess();
        return "Hello, World!";
    }
}
