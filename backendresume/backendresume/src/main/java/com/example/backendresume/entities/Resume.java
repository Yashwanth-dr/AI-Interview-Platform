package com.example.backendresume.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String fileName;

    private String filePath;

    public Resume() {
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}