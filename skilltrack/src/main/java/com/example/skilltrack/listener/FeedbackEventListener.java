package com.example.skilltrack.listener;

import com.example.skilltrack.entity.Notification;
import com.example.skilltrack.event.FeedbackSubmittedEvent;
import com.example.skilltrack.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeedbackEventListener {

    private final NotificationRepository notificationRepository;

    @Async
    @EventListener
    public void handleFeedbackSubmitted(FeedbackSubmittedEvent event) {

        if (event.getReviewerId().equals(event.getStudentId())) {
            return;
        }

        Notification notification = Notification.builder()
                .userId(event.getStudentId())
                .type("FEEDBACK")
                .title("New Feedback Received")
                .message(event.getReviewerName() +
                        " reviewed your project with " +
                        event.getRating() + "⭐")
                .referenceId(event.getProjectId())
                .build();

        notificationRepository.save(notification);
    }
}
