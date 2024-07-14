package com.alura.challenge.foroHub.topic;

import com.alura.challenge.foroHub.model.entity.Course;
import com.alura.challenge.foroHub.users.User;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
        @NotNull
        Long id,
        String title,
        String message,
        User user,
        Course course
) {
}
