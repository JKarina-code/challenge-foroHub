package com.alura.challenge.foroHub.topic;

import com.alura.challenge.foroHub.model.DTO.AnswerDTO;
import com.alura.challenge.foroHub.model.entity.Course;
import com.alura.challenge.foroHub.users.User;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.List;

public record TopicRegisterDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        Status status,
        @NotNull
        @Valid
        String  user,
        @NotNull
        @Valid
        String course,
        List<AnswerDTO> answers

) {

}
