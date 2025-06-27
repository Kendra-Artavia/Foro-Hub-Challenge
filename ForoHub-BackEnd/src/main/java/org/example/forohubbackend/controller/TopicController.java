package org.example.forohubbackend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.forohubbackend.domain.topic.CreateTopicRequest;
import org.example.forohubbackend.domain.topic.Topic;
import org.example.forohubbackend.domain.topic.TopicResponse;
import org.example.forohubbackend.domain.topic.UpdateTopicRequest;
import org.example.forohubbackend.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public ResponseEntity<Page<TopicResponse>> getTopics(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer year,
            @PageableDefault(size = 10, sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<TopicResponse> result = service.getTopics(courseName, year, pageable);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopic(@PathVariable Long id) {
        Topic topic = service.findById(id);
        TopicResponse response = new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.isStatus(),
                topic.getAuthor().getName(),
                topic.getCourse().getName()
        );
        return ResponseEntity.ok(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody @Valid UpdateTopicRequest dto) {
        try {
            service.updateTopic(id, dto);
            return ResponseEntity.ok("Topic updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        try {
            service.deleteTopic(id);
            return ResponseEntity.ok("Topic deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
