package org.example.forohubbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.forohubbackend.domain.ValidationException;
import org.example.forohubbackend.domain.course.Course;
import org.example.forohubbackend.domain.repository.CourseRepository;
import org.example.forohubbackend.domain.repository.ResponseRepository;
import org.example.forohubbackend.domain.repository.TopicRepository;
import org.example.forohubbackend.domain.repository.UserRepository;
import org.example.forohubbackend.domain.topic.CreateTopicRequest;
import org.example.forohubbackend.domain.topic.Topic;
import org.example.forohubbackend.domain.topic.TopicResponse;
import org.example.forohubbackend.domain.topic.UpdateTopicRequest;
import org.example.forohubbackend.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final ResponseRepository responseRepository;
    private final CourseRepository courseRepository;
    private final UserService userService;

    public void newTopic(CreateTopicRequest dto) {
        Course course = courseRepository.findByName(dto.courseName());
        if (course == null) throw new ValidationException("Course not found");

        User currentUser = userService.getCurrentUser();

        Topic topic = new Topic();
        topic.setTitle(dto.title());
        topic.setMessage(dto.message());
        topic.setCreationDate(LocalDate.now());
        topic.setStatus(false);
        topic.setAuthor(currentUser);
        topic.setCourse(course);

        topicRepository.save(topic);
    }

    public Page<TopicResponse> getTopics(String courseName, Integer year, Pageable pageable) {
        Page<Topic> topics;

        if (courseName != null || year != null) {
            topics = topicRepository.findByCourseNameOrYearOrderByCreationDateAsc(courseName, year, pageable);
        } else {
            topics = topicRepository.findAllByOrderByCreationDateAsc(pageable);
        }

        return topics.map(t -> new TopicResponse(
                t.getId(),
                t.getTitle(),
                t.getMessage(),
                t.getCreationDate(),
                t.isStatus(),
                t.getAuthor().getName(),
                t.getCourse().getName()
        ));
    }



    public Topic findById(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new ValidationException("Topic not found"));
    }


    public void updateTopic(Long id, UpdateTopicRequest dto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Topic not found"));

        Course course = courseRepository.findByName(dto.courseName());
        if (course == null) throw new ValidationException("Course not found");

        topic.setTitle(dto.title());
        topic.setMessage(dto.message());
        topic.setCourse(course);

        topicRepository.save(topic);
    }


    public void deleteTopic(Long id) {
        if (!topicRepository.findById(id).isPresent()) {
            throw new ValidationException("Topic not found");
        }
        topicRepository.deleteById(id);
    }


}
