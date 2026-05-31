package com.example.backendresume.repositories;

import com.example.backendresume.entities.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository
        extends JpaRepository<Resume, Long> {

    List<Resume> findByUserEmail(
            String userEmail
    );
}