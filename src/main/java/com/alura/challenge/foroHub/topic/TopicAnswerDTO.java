package com.alura.challenge.foroHub.topic;

public record TopicAnswerDTO(
        Long id,
        String title,
        String message,
        java.sql.Timestamp createDate,
        Status status,
        String userName,
        String courseName
) {
}
