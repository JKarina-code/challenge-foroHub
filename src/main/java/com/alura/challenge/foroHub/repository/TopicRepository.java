package com.alura.challenge.foroHub.repository;

import com.alura.challenge.foroHub.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitleAndMessage(String title, String message);
}
