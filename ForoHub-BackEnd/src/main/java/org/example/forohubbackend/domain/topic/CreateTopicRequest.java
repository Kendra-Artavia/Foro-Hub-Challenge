package org.example.forohubbackend.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record CreateTopicRequest(
        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotBlank
        String courseName
) {}
