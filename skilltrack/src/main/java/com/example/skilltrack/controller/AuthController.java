package com.example.skilltrack.controller;

import com.example.skilltrack.dto.LoginRequest;
import com.example.skilltrack.dto.SignupRequest;
import com.example.skilltrack.dto.AuthResponse;
import com.example.skilltrack.dto.ServiceResponse;
import com.example.skilltrack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skilltrack/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ServiceResponse<AuthResponse>> signup(
            @RequestBody SignupRequest request) {

        AuthResponse response = authService.signup(request);

        return ResponseEntity.ok(
                ServiceResponse.success("User registered successfully", response)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ServiceResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(
                ServiceResponse.success("Login successful", response)
        );
    }
}
