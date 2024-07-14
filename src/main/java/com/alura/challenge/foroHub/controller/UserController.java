package com.alura.challenge.foroHub.controller;


import com.alura.challenge.foroHub.repository.UserRepository;
import com.alura.challenge.foroHub.users.User;
import com.alura.challenge.foroHub.users.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void registerUser(@RequestBody @Valid UserDTO userDTO) {
        userRepository.save(new User(userDTO));
    }
}
