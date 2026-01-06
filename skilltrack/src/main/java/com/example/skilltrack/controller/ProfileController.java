package com.example.skilltrack.controller;

import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skilltrack")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/save")
    public ProfileDto createProfile(@RequestBody ProfileDto profileDTO) {
        return profileService.createProfile(profileDTO);
    }

//    @GetMapping("/{email}")
//    public ProfileDto getProfileByEmail(@PathVariable String email) {
//        return profileService.getProfileByEmail(email);
//    }
}
