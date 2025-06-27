package org.example.forohubbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.forohubbackend.domain.repository.ResponseRepository;
import org.example.forohubbackend.domain.repository.TopicRepository;
import org.example.forohubbackend.domain.topic.CreateTopicRequest;
import org.example.forohubbackend.domain.topic.Topic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final ResponseRepository responseRepository;


    public void newTopic(CreateTopicRequest createTopicRequest) {
        topicRepository.save(new Topic(createTopicRequest));
    }
}
