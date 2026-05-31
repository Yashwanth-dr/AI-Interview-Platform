
package com.example.backendresume.services;

import org.springframework.stereotype.Service;

import com.example.backendresume.dto.EvaluationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AIService {
    public EvaluationResponse evaluateAnswer(
        String answer
) {

    int score;

    String feedback;

    if(answer.length() > 150){

        score = 90;

        feedback =
                "Excellent answer. Detailed explanation provided.";
    }
    else if(answer.length() > 80){

        score = 75;

        feedback =
                "Good answer. Add more examples and details.";
    }
    else{

        score = 50;

        feedback =
                "Answer is too short. Explain concepts in detail.";
    }

    return new EvaluationResponse(
            score,
            feedback
    );
}

    // GENERATE QUESTIONS
    public String generateQuestion(
            String domain
    ) {

        List<String> questions =
                new ArrayList<>();

        // JAVA QUESTIONS
        if (domain.equalsIgnoreCase("Java")) {

            questions.add(
                    "Explain OOP concepts in Java"
            );

            questions.add(
                    "Difference between JDK JRE and JVM"
            );

            questions.add(
                    "Explain multithreading in Java"
            );

            questions.add(
                    "What is inheritance in Java"
            );
        }

        // DSA QUESTIONS
        else if (domain.equalsIgnoreCase("DSA")) {

            questions.add(
                    "Explain merge sort algorithm"
            );

            questions.add(
                    "Difference between stack and queue"
            );

            questions.add(
                    "Explain time complexity"
            );

            questions.add(
                    "What is binary search tree"
            );
        }

        // DBMS QUESTIONS
        else if (domain.equalsIgnoreCase("DBMS")) {

            questions.add(
                    "Explain normalization"
            );

            questions.add(
                    "Difference between SQL and NoSQL"
            );

            questions.add(
                    "What are joins in DBMS"
            );

            questions.add(
                    "Explain ACID properties"
            );
        }

        // OS QUESTIONS
        else if (domain.equalsIgnoreCase("OS")) {

            questions.add(
                    "Explain deadlock in OS"
            );

            questions.add(
                    "Difference between process and thread"
            );

            questions.add(
                    "Explain paging in OS"
            );

            questions.add(
                    "What is CPU scheduling"
            );
        }

        // RANDOM QUESTION
        Random random = new Random();

        return questions.get(
                random.nextInt(questions.size())
        );
    }
}

