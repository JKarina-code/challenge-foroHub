package com.alura.challenge.foroHub.model.entity;

import com.alura.challenge.foroHub.model.DTO.AnswerDTO;
import com.alura.challenge.foroHub.topic.Topic;
import com.alura.challenge.foroHub.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "answer")
@Entity(name = "Answer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    private String message;
    private String createDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private Solution solution;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Answer(AnswerDTO answer) {
        this.message = answer.message().toString();
        this.createDate = answer.createDate();
        this.user = answer.user();
        this.solution = answer.solution();
        this.topic = answer.topic();
    }
}
