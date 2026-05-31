package com.example.backendresume.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GroqService {

    private final RestTemplate restTemplate;

    public GroqService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${groq.api.key}")
    private String apiKey;

    public String askGroq(String prompt) {

        try {

            String url =
                    "https://api.groq.com/openai/v1/chat/completions";

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            headers.setBearerAuth(apiKey);

            Map<String, Object> body =
                    new HashMap<>();

            body.put(
                    "model",
                    "llama-3.3-70b-versatile"
            );

            List<Map<String, String>> messages =
                    List.of(
                            Map.of(
                                    "role",
                                    "user",
                                    "content",
                                    prompt
                            )
                    );

            body.put(
                    "messages",
                    messages
            );

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(
                            body,
                            headers
                    );

            ResponseEntity<String> response =
                    restTemplate.postForEntity(
                            url,
                            request,
                            String.class
                    );

            String responseBody =
                    response.getBody();

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(responseBody);

            return root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {

            e.printStackTrace();

            return "Error while calling Groq API";
        }
    }
}