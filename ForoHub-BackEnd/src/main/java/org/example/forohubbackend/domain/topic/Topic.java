package org.example.forohubbackend.domain.topic;

import jakarta.persistence.*;
import lombok.*;
import org.example.forohubbackend.domain.course.Course;
import org.example.forohubbackend.domain.user.User;

import java.time.LocalDate;

@Entity
@Table(name = "topics", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "message"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String message;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;


}
