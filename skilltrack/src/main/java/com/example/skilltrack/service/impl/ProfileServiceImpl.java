package com.example.skilltrack.service.impl;

import com.example.skilltrack.dto.UpdateFeedbackRequest;
import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.dto.ProjectDto;
import com.example.skilltrack.entity.Profile;
import com.example.skilltrack.entity.Project;
import com.example.skilltrack.entity.ProjectFeedback;
import com.example.skilltrack.entity.User;
import com.example.skilltrack.event.FeedbackSubmittedEvent;
import com.example.skilltrack.repository.ProfileRepo;
import com.example.skilltrack.repository.ProjectFeedbackRepository;
import com.example.skilltrack.repository.ProjectRepo;
import com.example.skilltrack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ProjectFeedbackRepository projectFeedbackRepository;

    @Autowired
    private final ApplicationEventPublisher eventPublisher;

    public ProfileServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    // -------- PROFILE --------
    @Override
    public ProfileDto createProfile(ProfileDto dto) {
        Profile profile = mapToEntity(dto);
        Profile saved = profileRepo.save(profile);
        return mapToDTO(saved);
    }

    @Override
    public void updateProfile(String email, ProfileDto dto) {

        Profile existingProfile = profileRepo.findByEmail(email);

        if (existingProfile == null) {
            throw new RuntimeException("Profile not found with email: " + email);
        }

        existingProfile.setName(dto.getName());
        existingProfile.setBio(dto.getBio());
        existingProfile.setEducation(dto.getEducation());
        existingProfile.setMajor(dto.getMajor());
        existingProfile.setGraduationYear(dto.getGraduationYear());
        existingProfile.setSkills(dto.getSkills());

        profileRepo.save(existingProfile);
    }


    // -------- PROJECTS --------
    @Override
    public String saveProjects(List<ProjectDto> projects,User user) {
        List<Project> entities = projects.stream()
                .map(dto -> mapProjectToEntity(dto, user))
                .collect(Collectors.toList());

        projectRepo.saveAll(entities);
        return "Projects saved successfully";
    }

    @Override
    public Page<Project> getAllProjects(String search, Pageable pageable) {
        return projectRepo.findAll(pageable);
    }


    @Override
    public String saveProjectFeedback(UpdateFeedbackRequest request, User reviewer) {

        Project project = projectRepo.findById(request.getProjectId())
                .orElseThrow(() ->
                        new RuntimeException("Project not found with id: " + request.getProjectId()));

        ProjectFeedback existingFeedback =
                projectFeedbackRepository.findByProject(project);

        ProjectFeedback savedFeedback;

        if (existingFeedback != null) {
            existingFeedback.setStarRating(request.getStarRating());
            existingFeedback.setWrittenFeedback(request.getWrittenFeedback());
            savedFeedback = projectFeedbackRepository.save(existingFeedback);
        } else {
            ProjectFeedback feedback = ProjectFeedback.builder()
                    .project(project)
                    .reviewer(reviewer)
                    .starRating(request.getStarRating())
                    .writtenFeedback(request.getWrittenFeedback())
                    .build();

            savedFeedback = projectFeedbackRepository.save(feedback);
        }

        // Prevent self-review notification
        if (!reviewer.getId().equals(project.getUser().getId())) {
            eventPublisher.publishEvent(
                    new FeedbackSubmittedEvent(
                            project.getId(),
                            project.getUser().getId(),
                            reviewer.getId(),
                            reviewer.getEmail(),
                            request.getStarRating()
                    )
            );
        }

        return "Feedback saved successfully";
    }


    // -------- MAPPERS --------
    private Profile mapToEntity(ProfileDto dto) {
        Profile p = new Profile();
        p.setName(dto.getName());
        p.setEmail(dto.getEmail());
        p.setBio(dto.getBio());
        p.setEducation(dto.getEducation());
        p.setMajor(dto.getMajor());
        p.setGraduationYear(dto.getGraduationYear());
        p.setSkills(dto.getSkills());
        return p;
    }

    private ProfileDto mapToDTO(Profile p) {
        ProfileDto dto = new ProfileDto();
        dto.setName(p.getName());
        dto.setEmail(p.getEmail());
        dto.setBio(p.getBio());
        dto.setEducation(p.getEducation());
        dto.setMajor(p.getMajor());
        dto.setGraduationYear(p.getGraduationYear());
        dto.setSkills(p.getSkills());
        return dto;
    }

    private Project mapProjectToEntity(ProjectDto dto,User user) {
        Project p = new Project();
        p.setId(dto.getId());
        p.setTitle(dto.getTitle());
        p.setDescription(dto.getDescription());
        p.setTechnologies(dto.getTechnologies());
        p.setImageUrl(dto.getImageUrl());
        p.setProjectUrl(dto.getProjectUrl());
        p.setGithubUrl(dto.getGithubUrl());
        p.setStartDate(dto.getStartDate());
        p.setEndDate(dto.getEndDate());
        p.setFeatured(dto.isFeatured());
        p.setUser(user);
        return p;
    }


}
