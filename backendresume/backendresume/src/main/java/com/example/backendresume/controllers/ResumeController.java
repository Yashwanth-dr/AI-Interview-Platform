package com.example.backendresume.controllers;

import com.example.backendresume.entities.Resume;
import com.example.backendresume.services.GroqService;
import com.example.backendresume.services.PDFService;
import com.example.backendresume.services.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("origins = http://localhost:5173")
public class ResumeController {

    @Autowired
    private PDFService pdfService;
@Autowired
private GroqService groqService;
    @Autowired
    private ResumeService service;

    @PostMapping("/upload")
    public Resume uploadResume(

            @RequestParam String email,

            @RequestParam MultipartFile file

    ) throws Exception {

        System.out.println("UPLOAD API HIT");
        System.out.println("EMAIL = " + email);
        System.out.println("FILE = " + file.getOriginalFilename());

        return service.uploadResume(
                email,
                file
        );
    }

    @GetMapping("/extract/{id}")
    public String extractResume(
            @PathVariable Long id
    ) throws Exception {

        Resume resume =
                service.getResumeById(id);

        return pdfService.extractText(
                resume.getFilePath()
        );
    }
    @GetMapping("/analyze/{id}")
public String analyzeResume(
        @PathVariable Long id
) throws Exception {

    Resume resume =
            service.getResumeById(id);

    String text =
            pdfService.extractText(
                    resume.getFilePath()
            );

    String prompt =

            "Analyze this resume and give:\n" +

            "1. Skills\n" +

            "2. Technologies\n" +

            "3. Recommended Interview Domains\n\n"

            + text;

    return groqService.askGroq(
            prompt
    );
}
}