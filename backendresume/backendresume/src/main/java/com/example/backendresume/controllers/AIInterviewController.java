package com.example.backendresume.controllers;

import com.example.backendresume.services.InterviewAIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin("origins = http://localhost:5173")
public class AIInterviewController {

    @Autowired
    private InterviewAIService service;

    @GetMapping("/questions/{domain}")
    public String generateQuestions(
            @PathVariable String domain
    ) {

        return service.generateQuestions(domain);
    }
}