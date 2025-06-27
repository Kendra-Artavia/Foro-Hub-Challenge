package org.example.forohubbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.forohubbackend.domain.repository.TopicRepository;
import org.example.forohubbackend.domain.topic.CreateTopicRequest;
import org.example.forohubbackend.domain.user.RegisterRequest;
import org.example.forohubbackend.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    private final TopicService service;

    @PostMapping("/new")
    public ResponseEntity<?> newTopic(@RequestBody @Valid CreateTopicRequest createTopicRequest) {
        try {
            service.newTopic(createTopicRequest);
            return ResponseEntity.ok("New topic registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }


}
