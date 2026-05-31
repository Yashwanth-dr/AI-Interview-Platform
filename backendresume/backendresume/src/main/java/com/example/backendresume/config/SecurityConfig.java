
package com.example.backendresume.config;

import com.example.backendresume.security.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // SECURITY CONFIG
    @Bean
public SecurityFilterChain securityFilterChain(
        HttpSecurity http
) throws Exception {

    http

            // ENABLE CORS
            .cors(cors -> {})

            // DISABLE CSRF
            .csrf(csrf -> csrf.disable())

            // JWT SESSION
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )

            // AUTHORIZATION
            .authorizeHttpRequests(auth -> auth

                    .requestMatchers(
                            "/api/auth/**",
                            "/api/analytics/test",
                            "/api/resume/upload"
                    )
                    .permitAll()

                    .anyRequest()
                    .authenticated()
            );

    // ADD JWT FILTER
   http.addFilterBefore(
            jwtFilter,
            UsernamePasswordAuthenticationFilter.class
   );

    return http.build();
}
}

