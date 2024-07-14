package com.alura.challenge.foroHub.users;

import com.alura.challenge.foroHub.model.DTO.ProfileDTO;
import jakarta.validation.constraints.*;
import java.util.List;
public record UserDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        List<ProfileDTO> profiles) {

        public String getName() {
                return this.name;
        }

        public String getEmail() {
                return this.email;
        }

        public String getPassword() {
                return this.password;
        }
}

