package com.example.backendresume.controllers;

import com.example.backendresume.services.GroqService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    @Autowired
    private GroqService groqService;

    @GetMapping("/{domain}")
    public String generateQuestions(
            @PathVariable String domain
    ) {

        String prompt =

                "Generate 5 interview questions for "
                        + domain
                        + ".\n"
                        + "Return only the questions.\n"
                        + "Each question should be on a new line.\n"
                        + "Do not provide answers.";

        return groqService.askGroq(
                prompt
        );
    }
}