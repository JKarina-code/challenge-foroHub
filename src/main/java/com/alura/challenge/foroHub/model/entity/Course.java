package com.alura.challenge.foroHub.model.entity;

import com.alura.challenge.foroHub.model.DTO.CourseDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "course")
@Entity(name = "Course")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    public Course(CourseDTO course) {
        this.name = course.getName();
        this.category = course.getCategory();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
