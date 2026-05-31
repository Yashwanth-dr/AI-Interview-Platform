package com.example.backendresume.controllers;

import com.example.backendresume.dto.EvaluationResponse;
import com.example.backendresume.services.AIService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:5173")

public class AIController {

    @Autowired
    private AIService service;

    // GENERATE QUESTION API
    @GetMapping("/question/{domain}")
    public Map<String, String> generateQuestion(
            @PathVariable String domain
    ) {

        String question =
                service.generateQuestion(domain);

        Map<String, String> response =
                new HashMap<>();

        response.put("domain", domain);

        response.put("question", question);

        return response;
    }
    @PostMapping("/evaluate")
public EvaluationResponse evaluateAnswer(
        @RequestBody String answer
){

    return service.evaluateAnswer(answer);
}
}

