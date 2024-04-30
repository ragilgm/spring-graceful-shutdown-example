package com.examplegracefullshutdown.demo.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CpuHealthIndicator implements HealthIndicator {

    private static final double CPU_THRESHOLD = 0.7; // 70%

    @Override
    public Health health() {
        double cpuUsage = getSystemCpuUsage(); // Implementasi untuk mendapatkan penggunaan CPU
        if (cpuUsage > CPU_THRESHOLD) {
            return Health.down().withDetail("CPU Usage", cpuUsage).build();
        }
        return Health.up().withDetail("CPU Usage", cpuUsage).build();
    }

    private double getSystemCpuUsage() {
        // Implementasi untuk mendapatkan penggunaan CPU dari sistem, misalnya menggunakan ManagementFactory.getOperatingSystemMXBean()
        return 0.0; // Ganti dengan implementasi sesuai kebutuhan Anda
    }
}
