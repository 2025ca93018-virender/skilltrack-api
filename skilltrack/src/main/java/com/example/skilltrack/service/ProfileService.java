package com.example.skilltrack.service;

import com.example.skilltrack.dto.UpdateFeedbackRequest;
import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.dto.ProjectDto;
import com.example.skilltrack.entity.Project;
import com.example.skilltrack.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {

    ProfileDto createProfile(ProfileDto profileDTO);

    void updateProfile(String email, ProfileDto profileDto);

    String saveProjects(List<ProjectDto> projects,User user);

    Page<Project> getAllProjects(String search, Pageable pageable);

    public String saveProjectFeedback(UpdateFeedbackRequest request, User reviewer);

//    ProfileDto getProfileByEmailofileByEmail(String email);
}
