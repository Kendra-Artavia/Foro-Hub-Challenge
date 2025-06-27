package org.example.forohubbackend.domain.topic;


import java.time.LocalDate;

public record TopicResponse(
        Long id,
        String title,
        String message,
        LocalDate creationDate,
        boolean status,
        String authorName,
        String courseName
) {
}

