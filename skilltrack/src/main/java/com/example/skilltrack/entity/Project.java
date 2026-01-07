package com.example.skilltrack.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    private String id;

    private String title;
    private String description;

    @ElementCollection
    @CollectionTable(name = "project_technologies",
            joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "technology")
    private List<String> technologies;

    private String imageUrl;
    private String projectUrl;
    private String githubUrl;

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean featured;

    // getters & setters
}
