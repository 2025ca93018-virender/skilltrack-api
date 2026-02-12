package com.example.skilltrack.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedbackSubmittedEvent {

    private String projectId;
    private Long studentId;
    private Long reviewerId;
    private String reviewerName;
    private int rating;
}
