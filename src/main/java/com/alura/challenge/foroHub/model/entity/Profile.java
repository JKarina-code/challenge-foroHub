package com.alura.challenge.foroHub.model.entity;

import com.alura.challenge.foroHub.model.DTO.ProfileDTO;
import com.alura.challenge.foroHub.users.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Table(name = "profile")
@Entity(name = "Profile")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "profiles")
    private Set<User> users = new HashSet<>();

    public Profile(ProfileDTO profiles) {
        this.name = profiles.name();
    }
}
