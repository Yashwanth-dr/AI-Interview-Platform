package com.example.backendresume.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewAIService {

    @Autowired
    private GroqService groqService;

    public String generateQuestions(String domain) {

        String prompt = """
                Generate 10 interview questions for
                """ + domain +
                """
                
                Return only questions.
                """;

        return groqService.askGroq(prompt);
    }
}