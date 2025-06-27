package org.example.forohubbackend.domain.repository;

import org.example.forohubbackend.domain.response.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
