package com.examplegracefullshutdown.demo.configuration;

import com.examplegracefullshutdown.demo.interceptor.CpuUsageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CpuUsageInterceptor cpuUsageInterceptor() {
        return new CpuUsageInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cpuUsageInterceptor());
    }
}
