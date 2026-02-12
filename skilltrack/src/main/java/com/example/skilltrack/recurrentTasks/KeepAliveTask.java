package com.example.skilltrack.recurrentTasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class KeepAliveTask {

    private final RestTemplate restTemplate = new RestTemplate();

    // Every 14 minutes to avoid 15 minute inactivity on render
    @Scheduled(fixedRate = 840000)
    public void selfPing() {
        try {
            String productionUrl = "https://skilltrack-api-sm3r.onrender.com/skilltrack/healthcheck";
            restTemplate.getForEntity(productionUrl, String.class);
            System.out.println("Health check successful at " + LocalDateTime.now());
        } catch (Exception e) {
            System.err.println("Health Check failed: " + e.getMessage());
        }
    }
}
