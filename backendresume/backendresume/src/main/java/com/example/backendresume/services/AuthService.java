
package com.example.backendresume.services;

import com.example.backendresume.entities.User;
import com.example.backendresume.repositories.UserRepository;
import com.example.backendresume.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // REGISTER USER
    public String register(User user) {

        // CHECK EMAIL
        if (repo.findByEmail(user.getEmail()).isPresent()) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        // ENCODE PASSWORD
        user.setPassword(
                encoder.encode(user.getPassword())
        );

        repo.save(user);

        return "User Registered Successfully";
    }

    // LOGIN USER
    public String login(User user) {

        User existing = repo.findByEmail(
                user.getEmail()
        ).orElseThrow(() ->
                new RuntimeException("User Not Found")
        );

        // CHECK PASSWORD
        if (!encoder.matches(
                user.getPassword(),
                existing.getPassword()
        )) {

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        // GENERATE TOKEN
        return JwtUtil.generateToken(
                existing.getEmail()
        );
    }
}

