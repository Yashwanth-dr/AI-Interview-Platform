package com.example.backendresume.controllers;

import com.example.backendresume.dto.EvaluationRequest;
import com.example.backendresume.services.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluation")
@CrossOrigin("origins = http://localhost:5173")
public class EvaluationController {

    @Autowired
    private EvaluationService service;

    @PostMapping("/answer")
    public String evaluateAnswer(

            @RequestBody EvaluationRequest request

    ) {System.out.println("EVALUATION API HIT");


        return service.evaluateAnswer(
                request
        );
    }
}