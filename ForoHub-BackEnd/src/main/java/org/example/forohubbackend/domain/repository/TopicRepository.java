package org.example.forohubbackend.domain.repository;


import org.example.forohubbackend.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface TopicRepository extends JpaRepository<Topic, Long> {

//    @Query("SELECT t FROM Topic t ORDER BY t.creationDate ASC")
//    List<Topic> findAllOrderByCreationDateAsc();
//
//    @Query("""
//        SELECT t FROM Topic t
//        WHERE (:courseName IS NULL OR t.course.name = :courseName)
//          AND (:year IS NULL OR YEAR(t.creationDate) = :year)
//        ORDER BY t.creationDate ASC
//    """)
//    List<Topic> findByCourseNameOrYearOrderByCreationDateAsc(String courseName, Integer year);


    @Query("""
        SELECT t FROM Topic t
        WHERE (:courseName IS NULL OR t.course.name = :courseName)
        AND (:year IS NULL OR YEAR(t.creationDate) = :year)
        ORDER BY t.creationDate ASC
    """)
    Page<Topic> findByCourseNameOrYearOrderByCreationDateAsc(
            @Param("courseName") String courseName,
            @Param("year") Integer year,
            Pageable pageable
    );

    Page<Topic> findAllByOrderByCreationDateAsc(Pageable pageable);


     Optional<Topic> findById(Long id);
}
