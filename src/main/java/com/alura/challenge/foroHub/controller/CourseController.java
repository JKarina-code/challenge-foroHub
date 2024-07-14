package com.alura.challenge.foroHub.controller;

import com.alura.challenge.foroHub.model.DTO.CourseDTO;
import com.alura.challenge.foroHub.model.entity.Course;
import com.alura.challenge.foroHub.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public void registerCourse(@RequestBody @Valid CourseDTO courseDTO) {
        courseRepository.save(new Course(courseDTO));
    }


}
