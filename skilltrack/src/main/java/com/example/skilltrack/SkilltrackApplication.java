package com.example.skilltrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.skilltrack")
public class SkilltrackApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkilltrackApplication.class, args);
    }
}

