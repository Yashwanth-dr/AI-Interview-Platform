
package com.example.backendresume.services;

import com.example.backendresume.entities.Interview;

import com.example.backendresume.repositories.InterviewRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository repo;

    // SAVE INTERVIEW
    public Interview saveInterview(
            Interview interview
    ) {

        interview.setInterviewDate(
                LocalDateTime.now()
        );

        return repo.save(interview);
    }

    // GET USER INTERVIEWS
    public List<Interview> getUserInterviews(
            String email
    ) {

        return repo.findByUserEmail(email);
    }
}


