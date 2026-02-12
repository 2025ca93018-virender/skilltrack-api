package com.example.skilltrack.security;

import com.example.skilltrack.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !(authentication.getPrincipal() instanceof User user)) {
            throw new RuntimeException("Unauthorized");
        }

        return user;
    }
}
