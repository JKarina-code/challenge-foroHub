package com.alura.challenge.foroHub.repository;

import com.alura.challenge.foroHub.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    UserDetails findUserDetailsByName(String name);
}
