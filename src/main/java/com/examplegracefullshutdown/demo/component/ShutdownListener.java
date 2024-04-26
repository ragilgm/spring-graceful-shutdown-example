package com.examplegracefullshutdown.demo.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShutdownListener implements ApplicationListener<ContextClosedEvent> {
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

        log.info("{}",event);
        log.info(event.getApplicationContext().getApplicationName());
        log.info("{}",event.getSource());

        // Dapatkan informasi tentang thread yang sedang berjalan
        int activeThreadCount = taskExecutor.getActiveCount();
        int corePoolSize = taskExecutor.getCorePoolSize();
        int maxPoolSize = taskExecutor.getMaxPoolSize();

        log.info("Jumlah thread yang sedang berjalan: " + activeThreadCount);
        log.info("Jumlah thread inti: " + corePoolSize);
        log.info("Jumlah thread maksimal: " + maxPoolSize);

        taskExecutor.setAwaitTerminationSeconds(60*10);// 10 minutes
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.shutdown();
    }
}
