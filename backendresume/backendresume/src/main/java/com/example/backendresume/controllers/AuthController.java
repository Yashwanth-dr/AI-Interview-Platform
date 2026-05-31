
package com.example.backendresume.controllers;

import com.example.backendresume.entities.User;
import com.example.backendresume.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("origins = http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService service;

    // REGISTER API
    @PostMapping("/register")
    public String register(
            @RequestBody User user
    ) {

        return service.register(user);
    }

    // LOGIN API
    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody User user
    ) {

        String token = service.login(user);

        Map<String, String> response =
                new HashMap<>();

        response.put("token", token);

        return response;
    }
}

