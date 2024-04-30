package com.examplegracefullshutdown.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Slf4j
@EnableAsync
public class ScheduleConfiguration {


    @Async("TestTaskExecutor")
    @Scheduled(cron = "${scheduled.task.cron.expression}") // Executes at 12 PM every day
    public void performDailyTask() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("iterasi ke " + i);
        }
    }

}
