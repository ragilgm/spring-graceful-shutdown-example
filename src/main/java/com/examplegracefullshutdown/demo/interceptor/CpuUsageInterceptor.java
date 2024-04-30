package com.examplegracefullshutdown.demo.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class CpuUsageInterceptor implements HandlerInterceptor {

    @Autowired
    private MeterRegistry meterRegistry;


    private static final double CPU_THRESHOLD = 0.05; // 70%


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        double cpuUsage = meterRegistry.get("system.cpu.usage").gauge().value(); // Mendapatkan penggunaan CPU dari Micrometer
        if (cpuUsage > CPU_THRESHOLD) {
            response.getWriter().write("{\"errorMessage\":\"Maaf saat ini server kami sedang sibuk , silahkan mencoba lagi nanti\"}");
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return false;
        }
        return true;
    }


}
