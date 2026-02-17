package com.example.skilltrack.controller;

import com.example.skilltrack.dto.UpdateFeedbackRequest;
import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.dto.ProjectDto;
import com.example.skilltrack.entity.Project;
import com.example.skilltrack.entity.Role;
import com.example.skilltrack.entity.User;
import com.example.skilltrack.security.SecurityUtils;
import com.example.skilltrack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skilltrack")
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

    @GetMapping("/projects")
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        User user = SecurityUtils.getCurrentUser();
        if(user.getRole().equals(Role.TEACHER)) {
            return ResponseEntity.ok(profileService.getAllProjects(search, pageable));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access Denied: Only teachers can view all projects.");
        }
    }

    @PostMapping("/save/feedback")
    public String updateFeedback(@RequestBody UpdateFeedbackRequest request) {
        User reviewer = SecurityUtils.getCurrentUser();
        return profileService.saveProjectFeedback(request, reviewer);
    }

}
