package com.alura.challenge.foroHub.topic;

public record TopicListDTO(
        Long id,
        String title,
        String message,
        java.sql.Timestamp createDate,
        Status status,
        String userName,
        String courseName

) {
    public static TopicListDTO fromTopic(Topic topic) {
        return new TopicListDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                java.sql.Timestamp.valueOf(topic.getCreateDate()),
                topic.getStatus(),
                topic.getUser() != null ? topic.getUser().getName() : "User not available",
                topic.getCourse() != null ? topic.getCourse().getName() : "Course not available"
        );
    }
}
