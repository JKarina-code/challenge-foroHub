package com.alura.challenge.foroHub.controller;

import com.alura.challenge.foroHub.model.entity.Course;
import com.alura.challenge.foroHub.repository.CourseRepository;
import com.alura.challenge.foroHub.repository.ProfileRepository;
import com.alura.challenge.foroHub.repository.TopicRepository;
import com.alura.challenge.foroHub.repository.UserRepository;
import com.alura.challenge.foroHub.topic.*;
import com.alura.challenge.foroHub.users.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topics", description = "Topics Operations")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Register a new Topic", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<TopicAnswerDTO> registerTopic(@RequestBody @Valid TopicRegisterDTO topicRegisterDTO, UriComponentsBuilder uriComponentsBuilder) {

        if (topicRegisterDTO.status() != Status.ACTIVE) {
            throw new IllegalArgumentException("Topic status must to be ACTIVE.");
        }

        if (topicRepository.findByTitleAndMessage(topicRegisterDTO.title(), topicRegisterDTO.message()).isPresent()) {
            throw new IllegalArgumentException("Topic already exists.");
        }

        User user = userRepository.findByName(topicRegisterDTO.user())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        Course course = courseRepository.findByName(topicRegisterDTO.course())
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        Topic topic = new Topic(topicRegisterDTO);
        topic.setUser(user);
        topic.setCourse(course);
        topic.setCreateDate(LocalDateTime.now());
        topicRepository.save(topic);
        TopicAnswerDTO topicAnswerDTO = new TopicAnswerDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                java.sql.Timestamp.valueOf(topic.getCreateDate()),
                topic.getStatus(),
                topic.getUser().getName(),
                topic.getCourse().getName()
        );
        URI url = uriComponentsBuilder.path("/topics/data/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicAnswerDTO);
    }

    @GetMapping
    @Operation(summary = "List of registered topics", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<TopicListDTO>> topicsList(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "title") String[] sort
    ) {

        PageRequest pageRequest = PageRequest.of(page != null ? page : 0, size, Sort.by(sort));
        Page<Topic> topicosPage = topicRepository.findAll(pageRequest);
        List<TopicListDTO> topicListDTO = topicosPage.getContent().stream()
                .map(TopicListDTO::fromTopic)
                .collect(Collectors.toList());
        return ResponseEntity.ok(topicListDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get topic by ID", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<TopicListDTO> getTopicById(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found topic: " + id));
        TopicListDTO topicListDTO = TopicListDTO.fromTopic(topic);
        return ResponseEntity.ok(topicListDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update a topic", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<TopicAnswerDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO topicUpdateDTO) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.updateDTO(topicUpdateDTO);
            TopicAnswerDTO topicAnswerDTO = new TopicAnswerDTO(
                    topic.getId(),
                    topic.getTitle(),
                    topic.getMessage(),
                    java.sql.Timestamp.valueOf(topic.getCreateDate()),
                    topic.getStatus(),
                    topic.getUser().getName(),
                    topic.getCourse().getName()
            );
            return ResponseEntity.ok(topicAnswerDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found topic: " + id);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete Topic", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            topicRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found topic: " + id);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/data/{id}")
    @Operation(summary = "Consult topic data", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<TopicAnswerDTO> retornaDatosTopico(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        var dataTopic = new TopicAnswerDTO(topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                java.sql.Timestamp.valueOf(topic.getCreateDate()),
                topic.getStatus(),
                topic.getUser().getName(),
                topic.getCourse().getName()
        );
        return ResponseEntity.ok(dataTopic);
    }

}
