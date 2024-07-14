package com.alura.challenge.foroHub.model.DTO;

import com.alura.challenge.foroHub.model.entity.Solution;
import com.alura.challenge.foroHub.topic.Topic;
import com.alura.challenge.foroHub.users.User;

public record AnswerDTO(
        String message,
        Topic topic,
        String createDate,
        User user,
        Solution solution
) {
}
