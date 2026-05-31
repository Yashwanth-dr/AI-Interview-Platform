
package com.example.backendresume.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService service;

    public JwtFilter(CustomUserDetailsService service) {
        this.service = service;
    }

   
@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
) throws ServletException, IOException {

    // SKIP AUTH APIs
    String path = request.getServletPath();

    if (path.startsWith("/api/auth")) {

        filterChain.doFilter(request, response);

        return;
    }

    String authHeader =
            request.getHeader("Authorization");

    String token = null;
    String email = null;

    // CHECK TOKEN
    if (authHeader != null &&
            authHeader.startsWith("Bearer ")) {

        token = authHeader.substring(7);

        email = JwtUtil.extractEmail(token);
    }

    // AUTHENTICATE USER
    if (email != null &&
            SecurityContextHolder.getContext()
                    .getAuthentication() == null) {

        UserDetails userDetails =
                service.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        authToken.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(request)
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authToken);
    }

    filterChain.doFilter(request, response);
}
}

