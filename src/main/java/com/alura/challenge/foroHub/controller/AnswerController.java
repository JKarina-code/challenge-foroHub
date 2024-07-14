package com.alura.challenge.foroHub.controller;


import com.alura.challenge.foroHub.model.DTO.AnswerDTO;
import com.alura.challenge.foroHub.model.entity.Answer;
import com.alura.challenge.foroHub.repository.AnswerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping
    public void registerAnswer(@RequestBody @Valid AnswerDTO answerDTO) {
        answerRepository.save(new Answer(answerDTO));
    }
}
