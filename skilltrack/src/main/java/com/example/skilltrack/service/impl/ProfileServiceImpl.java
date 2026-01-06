package com.example.skilltrack.service.impl;

import com.example.skilltrack.dto.ProfileDto;
import com.example.skilltrack.repository.ProfileRepo;
import com.example.skilltrack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skilltrack.entity.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public ProfileDto createProfile(ProfileDto dto) {
        Profile profile = mapToEntity(dto);
        Profile saved = profileRepo.save(profile);
        return mapToDTO(saved);
    }

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


//    @Override
//    public ProfileDto getProfileByEmailofileByEmail(String email) {
//        return null;
//    }
}
