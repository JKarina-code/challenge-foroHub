package com.alura.challenge.foroHub.controller;

import com.alura.challenge.foroHub.infra.security.*;
import com.alura.challenge.foroHub.users.AuthenticationUserDTO;
import com.alura.challenge.foroHub.users.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticationUser(@RequestBody @Valid AuthenticationUserDTO authenticationUserDTO) {
        Authentication authtoken = new UsernamePasswordAuthenticationToken(authenticationUserDTO.name(), authenticationUserDTO.password());
        var userAuthenticated = authenticationManager.authenticate(authtoken);
        var JWTtoken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTtoken));
    }
}
