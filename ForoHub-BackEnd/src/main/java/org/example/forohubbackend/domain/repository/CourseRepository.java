package org.example.forohubbackend.domain.repository;


import org.example.forohubbackend.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
