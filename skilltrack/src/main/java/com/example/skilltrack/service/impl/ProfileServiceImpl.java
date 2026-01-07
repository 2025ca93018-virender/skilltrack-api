package com.example.skilltrack.service.impl;

import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.dto.ProjectDto;
import com.example.skilltrack.entity.Profile;
import com.example.skilltrack.entity.Project;
import com.example.skilltrack.repository.ProfileRepo;
import com.example.skilltrack.repository.ProjectRepo;
import com.example.skilltrack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private ProjectRepo projectRepo;

    // -------- PROFILE --------
    @Override
    public ProfileDto createProfile(ProfileDto dto) {
        Profile profile = mapToEntity(dto);
        Profile saved = profileRepo.save(profile);
        return mapToDTO(saved);
    }

    // -------- PROJECTS --------
    @Override
    public String saveProjects(List<ProjectDto> projects) {
        List<Project> entities = projects.stream()
                .map(this::mapProjectToEntity)
                .collect(Collectors.toList());

        projectRepo.saveAll(entities);
        return "Projects saved successfully";
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

    private Project mapProjectToEntity(ProjectDto dto) {
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
        return p;
    }
}
