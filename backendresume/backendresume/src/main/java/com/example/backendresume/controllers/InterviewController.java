
package com.example.backendresume.controllers;

import com.example.backendresume.entities.Interview;

import com.example.backendresume.services.InterviewService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")

public class InterviewController {

    @Autowired
    private InterviewService service;

    // SAVE INTERVIEW
    @PostMapping("/save")
    public Interview saveInterview(
            @RequestBody Interview interview
    ) {

        return service.saveInterview(interview);
    }

    // GET USER INTERVIEWS
    @GetMapping("/{email}")
    public List<Interview> getInterviews(
            @PathVariable String email
    ) {

        return service.getUserInterviews(email);
    }
}

