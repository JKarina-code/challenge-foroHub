package com.alura.challenge.foroHub.controller;


import com.alura.challenge.foroHub.model.DTO.ProfileDTO;
import com.alura.challenge.foroHub.model.entity.Profile;
import com.alura.challenge.foroHub.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping
    public void registerProfile(@RequestBody @Valid ProfileDTO profileDTO){
        profileRepository.save(new Profile(profileDTO));
    }
}
