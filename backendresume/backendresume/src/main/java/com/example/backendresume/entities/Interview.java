
package com.example.backendresume.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USER EMAIL
    private String userEmail;

    // DOMAIN
    private String domain;

    // QUESTION
    @Column(length = 5000)
    private String question;

    // USER ANSWER
    @Column(length = 5000)
    private String answer;

    // AI FEEDBACK
    @Column(length = 5000)
    private String feedback;

    // SCORE
    private int score;

    // DATE
    private LocalDateTime interviewDate;

    public Interview() {
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getDomain() {
        return domain;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getInterviewDate() {
        return interviewDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setInterviewDate(
            LocalDateTime interviewDate
    ) {
        this.interviewDate = interviewDate;
    }
}

