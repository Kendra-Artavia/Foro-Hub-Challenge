package org.example.forohubbackend.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateTopicRequest(
        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotNull
        LocalDate creationDate,

        @NotNull
        Boolean status,

        @NotNull
        Long authorId,

        @NotNull
        Long courseId
) {}
