package org.example.forohubbackend.domain.repository;

import org.example.forohubbackend.domain.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE LOWER(p.name) = LOWER(:name)")
    Profile findByName(String name);
}
