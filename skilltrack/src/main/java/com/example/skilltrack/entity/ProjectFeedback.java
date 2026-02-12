package com.example.skilltrack.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_feedback",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"project_id", "reviewer_id"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @Column(nullable = false)
    private Integer starRating;

    @Column(length = 1000)
    private String writtenFeedback;
}
