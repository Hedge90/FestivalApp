package com.festapp.festapp.repositories;

import com.festapp.festapp.entities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer,Long> {
    Optional<Organizer> findByEmail(String email);
}
