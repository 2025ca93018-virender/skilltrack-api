package com.example.skilltrack.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFeedbackRequest {
    private String projectId;

    @Min(value = 1, message = "Star rating must be at least 1")
    @Max(value = 5, message = "Star rating cannot be more than 5")
    private Integer starRating;


    @Size(max = 1000, message = "Feedback cannot exceed 1000 characters")
    private String writtenFeedback;
}
