package com.alura.challenge.foroHub.model.DTO;

import jakarta.validation.constraints.*;

public record CourseDTO(@NotBlank String name, @NotBlank String category) {
    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

}



