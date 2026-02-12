package com.example.skilltrack.service.impl;

import com.example.skilltrack.dto.NotificationResponse;
import com.example.skilltrack.entity.Notification;
import com.example.skilltrack.repository.NotificationRepository;
import com.example.skilltrack.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponse> getUserNotifications(Long userId) {

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(notification -> NotificationResponse.builder()
                        .id(notification.getId())
                        .title(notification.getTitle())
                        .message(notification.getMessage())
                        .read(notification.isReadStatus())
                        .createdAt(notification.getCreatedAt())
                        .referenceId(notification.getReferenceId())
                        .build())
                .toList();
    }

    @Override
    public void markAsRead(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setReadStatus(true);
        notificationRepository.save(notification);
    }
}
