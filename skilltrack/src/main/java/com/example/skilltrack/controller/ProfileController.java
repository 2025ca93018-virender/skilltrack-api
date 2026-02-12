package com.example.skilltrack.controller;

import com.example.skilltrack.dto.UpdateFeedbackRequest;
import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.dto.ProjectDto;
import com.example.skilltrack.entity.User;
import com.example.skilltrack.security.SecurityUtils;
import com.example.skilltrack.service.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skilltrack")
@Tag(name = "SkillTrack APIS")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/save/profiles")
    public String createProfile(@RequestBody ProfileDto profileDTO) {
        profileService.createProfile(profileDTO);
        return "Profile created successfully";
    }

    @PutMapping("/profiles/{email}")
    public String updateProfile(
            @PathVariable String email,
            @RequestBody ProfileDto profileDto) {

        profileService.updateProfile(email, profileDto);
        return "Profile updated successfully";
    }

    @PostMapping("/save/projects")
    public String saveProjects(@RequestBody List<ProjectDto> projects) {
        User user = SecurityUtils.getCurrentUser();
        return profileService.saveProjects(projects,user);
    }

    @PostMapping("/save/feedback")
    public String updateFeedback(@RequestBody UpdateFeedbackRequest request) {
        User reviewer = SecurityUtils.getCurrentUser();
        return profileService.saveProjectFeedback(request, reviewer);
    }

}
