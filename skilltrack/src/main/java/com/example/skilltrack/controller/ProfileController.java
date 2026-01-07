package com.example.skilltrack.controller;

import com.example.skilltrack.dto.ApiResponse;
import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.dto.ProjectDto;
import com.example.skilltrack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/save/projects")
    public String saveProjects(@RequestBody List<ProjectDto> projects) {
        return profileService.saveProjects(projects);
    }



//    @GetMapping("/{email}")
//    public ProfileDto getProfileByEmail(@PathVariable String email) {
//        return profileService.getProfileByEmail(email);
//    }
}
