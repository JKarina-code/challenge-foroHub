package com.alura.challenge.foroHub.topic;

import com.alura.challenge.foroHub.model.DTO.AnswerDTO;
import com.alura.challenge.foroHub.model.entity.*;
import com.alura.challenge.foroHub.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topic")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Topic(TopicRegisterDTO topicRegisterDTO) {
        this.title = topicRegisterDTO.title();
        this.message = topicRegisterDTO.message();
        this.createDate = LocalDateTime.now();
        this.status = topicRegisterDTO.status();
        this.user = user;
        this.course = course;
        this.answers = new ArrayList<>();
        if (topicRegisterDTO.answers() != null) {
            for (AnswerDTO answer : topicRegisterDTO.answers()) {
                this.answers.add(new Answer(answer));
            }
        }
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void updateDTO(TopicUpdateDTO topicUpdateDTO) {
        if (topicUpdateDTO.title() != null) {
            this.title = topicUpdateDTO.title();
        }
        if (topicUpdateDTO.message() != null) {
            this.message = topicUpdateDTO.message();
        }
        if (topicUpdateDTO.user() != null) {
            this.user = topicUpdateDTO.user();
        }
        if (topicUpdateDTO.course() != null) {
            this.course = topicUpdateDTO.course();
        }
    }
}
