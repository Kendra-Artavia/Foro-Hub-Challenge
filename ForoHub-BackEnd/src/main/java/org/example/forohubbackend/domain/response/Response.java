package org.example.forohubbackend.domain.response;

import jakarta.persistence.*;
import lombok.*;
import org.example.forohubbackend.domain.topic.Topic;
import org.example.forohubbackend.domain.user.User;

import java.time.LocalDate;

@Entity
@Table(name = "responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "is_solution")
    private boolean isSolution;
}
