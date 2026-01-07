package com.example.skilltrack.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {

    private String id;
    private String title;
    private String description;
    private List<String> technologies;
    private String imageUrl;
    private String projectUrl;
    private String githubUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean featured;

}
