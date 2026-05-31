package com.example.backendresume.controllers;

import com.example.backendresume.entities.InterviewResult;
import com.example.backendresume.repositories.InterviewResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:5173")
public class AnalyticsController {

    @Autowired
    private InterviewResultRepository repository;

    @GetMapping("/history")
public List<InterviewResult> history(
        @RequestParam String email
) {
    return repository.findByEmail(email);
}

@GetMapping("/total")
public long total(
        @RequestParam String email
) {
    return repository.countByEmail(email);
}

@GetMapping("/average")
public double average(
        @RequestParam String email
) {

    return repository
            .findByEmail(email)
            .stream()
            .mapToInt(
                    InterviewResult::getScore
            )
            .average()
            .orElse(0);
}
}