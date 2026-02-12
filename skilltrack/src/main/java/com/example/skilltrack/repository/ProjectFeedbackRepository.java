package com.example.skilltrack.repository;

import com.example.skilltrack.entity.Project;
import com.example.skilltrack.entity.ProjectFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectFeedbackRepository extends JpaRepository<ProjectFeedback, Long> {
    ProjectFeedback findByProject(Project project);
}
