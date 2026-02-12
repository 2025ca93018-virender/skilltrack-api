package com.example.skilltrack.dto;

import com.example.skilltrack.entity.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private Role role;
}

