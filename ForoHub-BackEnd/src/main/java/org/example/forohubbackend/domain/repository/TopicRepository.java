package org.example.forohubbackend.domain.repository;

import org.example.forohubbackend.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
