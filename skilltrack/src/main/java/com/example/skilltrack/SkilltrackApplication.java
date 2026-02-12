package com.example.skilltrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example.skilltrack")
@EnableScheduling
public class SkilltrackApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkilltrackApplication.class, args);
    }
}

