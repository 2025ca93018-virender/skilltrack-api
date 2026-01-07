package com.example.skilltrack.service;

import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.dto.ProjectDto;

import java.util.List;

public interface ProfileService {

    ProfileDto createProfile(ProfileDto profileDTO);

    void updateProfile(String email, ProfileDto profileDto);

    String saveProjects(List<ProjectDto> projects);


//    ProfileDto getProfileByEmailofileByEmail(String email);
}
