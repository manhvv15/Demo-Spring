package com.example.demo_spring_boot.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductSyncJob {

    // Chạy mỗi 10 giây để demo
    @Scheduled(fixedRate = 10000)
    public void syncProductJob() {
        System.out.println("🕒 [ProductSyncJob] Syncing products with external system... " + System.currentTimeMillis());
        // Gọi service để thực hiện logic nếu cần
    }

    // Ví dụ chạy 1 lần mỗi ngày lúc 2h sáng
    @Scheduled(cron = "0 45 11 * * ?")
    public void dailyCleanup() {
        System.out.println("🧹 [ProductSyncJob] Running daily cleanup...");
    }
}
