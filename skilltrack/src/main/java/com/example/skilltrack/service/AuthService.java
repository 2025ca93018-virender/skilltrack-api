package com.example.skilltrack.service;

import com.example.skilltrack.dto.AuthResponse;
import com.example.skilltrack.dto.LoginRequest;
import com.example.skilltrack.dto.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
