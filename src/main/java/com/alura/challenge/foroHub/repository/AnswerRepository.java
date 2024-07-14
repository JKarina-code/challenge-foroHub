package com.alura.challenge.foroHub.repository;

import com.alura.challenge.foroHub.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
