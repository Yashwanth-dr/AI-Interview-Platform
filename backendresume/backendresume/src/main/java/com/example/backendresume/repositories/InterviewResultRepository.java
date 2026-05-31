package com.example.backendresume.repositories;

import com.example.backendresume.entities.InterviewResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewResultRepository
        extends JpaRepository<InterviewResult, Long> {

    List<InterviewResult> findByEmail(
            String email
    );

    long countByEmail(
            String email
    );
}