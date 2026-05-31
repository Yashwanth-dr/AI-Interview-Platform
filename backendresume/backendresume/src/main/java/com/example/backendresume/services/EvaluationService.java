package com.example.backendresume.services;

import com.example.backendresume.dto.EvaluationRequest;
import com.example.backendresume.entities.InterviewResult;
import com.example.backendresume.repositories.InterviewResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    @Autowired
    private GroqService groqService;

    @Autowired
    private InterviewResultRepository repository;

    public String evaluateAnswer(
            EvaluationRequest request
    ) {

        String prompt =
        "Question:\n"
        + request.getQuestion()

        + "\n\nAnswer:\n"
        + request.getAnswer()

        + "\n\nEvaluate this answer."

        + "\nReturn EXACTLY in this format:"

        + "\nScore: <number>"

        + "\nFeedback: <feedback>"

        + "\nSuggestions: <suggestions>";
        String feedback =
                groqService.askGroq(prompt);

        InterviewResult result =
                new InterviewResult();

        result.setEmail(
                request.getEmail()
        );

        result.setDomain(
                request.getDomain()
        );

        result.setQuestion(
                request.getQuestion()
        );

        result.setAnswer(
                request.getAnswer()
        );

        result.setFeedback(
                feedback
        );
int score = 0;

try {

    java.util.regex.Pattern pattern =
            java.util.regex.Pattern.compile("(\\d+)");

    java.util.regex.Matcher matcher =
            pattern.matcher(feedback);

    if (matcher.find()) {

        score = Integer.parseInt(
                matcher.group(1)
        );
    }

} catch (Exception e) {

    score = 0;
}

result.setScore(score);
        

        repository.save(
                result
        );

        return feedback;
    }
}