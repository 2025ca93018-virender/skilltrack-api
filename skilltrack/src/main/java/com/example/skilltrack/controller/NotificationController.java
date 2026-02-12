package com.example.skilltrack.controller;

import com.example.skilltrack.dto.NotificationResponse;
import com.example.skilltrack.entity.User;
import com.example.skilltrack.repository.UserRepository;
import com.example.skilltrack.security.SecurityUtils;
import com.example.skilltrack.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skilltrack/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @GetMapping
    public List<NotificationResponse> getMyNotifications() {

        User user = SecurityUtils.getCurrentUser();

        return notificationService.getUserNotifications(user.getId());
    }

    @PutMapping("/{notificationId}/read")
    public void markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
    }
}
