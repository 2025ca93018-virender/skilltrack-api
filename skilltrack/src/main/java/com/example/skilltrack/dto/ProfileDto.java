package com.example.skilltrack.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileDto {

    private String name;
    private String email;
    private String bio;
    private String education;
    private String major;
    private String graduationYear;
    private List<String> skills;

    // getters and setters
}
