package com.alura.challenge.foroHub.repository;

import com.alura.challenge.foroHub.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
