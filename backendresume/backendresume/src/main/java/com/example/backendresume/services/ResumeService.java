package com.example.backendresume.services;

import com.example.backendresume.entities.Resume;
import com.example.backendresume.repositories.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository repo;
    public Resume getResumeById(Long id) {

    return repo.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Resume Not Found"
                    ));
}

    public Resume uploadResume(
            String email,
            MultipartFile file
    ) throws IOException {

        String uploadDir =
                System.getProperty("user.dir")
                        + File.separator
                        + "uploads";

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName =
                file.getOriginalFilename();

        String filePath =
                uploadDir
                        + File.separator
                        + fileName;

        System.out.println("UPLOAD DIRECTORY = " + uploadDir);
        System.out.println("FILE PATH = " + filePath);

        file.transferTo(
                new File(filePath)
        );

        Resume resume =
                new Resume();

        resume.setUserEmail(email);
        resume.setFileName(fileName);
        resume.setFilePath(filePath);

        return repo.save(resume);
    }
}