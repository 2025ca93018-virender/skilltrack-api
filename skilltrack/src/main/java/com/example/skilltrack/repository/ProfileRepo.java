package com.example.skilltrack.repository;

import com.example.skilltrack.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Profile findByEmail(String email);
}
