package com.example.backendresume.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "interview_results")
public class InterviewResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String domain;

    @Column(length = 2000)
    private String question;

    @Column(length = 5000)
    private String answer;

    @Column(length = 5000)
    private String feedback;

    private Integer score;

    // Default Constructor
    public InterviewResult() {
    }

    // Parameterized Constructor
    public InterviewResult(
            Long id,
            String email,
            String domain,
            String question,
            String answer,
            String feedback,
            Integer score) {

        this.id = id;
        this.email = email;
        this.domain = domain;
        this.question = question;
        this.answer = answer;
        this.feedback = feedback;
        this.score = score;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "InterviewResult{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", domain='" + domain + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", feedback='" + feedback + '\'' +
                ", score=" + score +
                '}';
    }
}