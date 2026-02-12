package com.example.skilltrack.service;

import com.example.skilltrack.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationResponse> getUserNotifications(Long userId);

    void markAsRead(Long notificationId);
}
