package com.example.skilltrack.controller;
import com.example.skilltrack.dto.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skilltrack/healthcheck")
@RequiredArgsConstructor
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<ServiceResponse<Void>> healthCheck() {

        return ResponseEntity.ok(
                ServiceResponse.success("User registered successfully",null)
        );
    }
}
