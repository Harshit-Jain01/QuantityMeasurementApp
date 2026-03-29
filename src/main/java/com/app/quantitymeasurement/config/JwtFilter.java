package com.app.quantitymeasurement.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.quantitymeasurement.security.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain chain)
            throws ServletException, java.io.IOException {

        String path = request.getRequestURI();
        System.out.println("JWT FILTER HIT: " + path);

        // ✅ SKIP PUBLIC ROUTES (VERY IMPORTANT FIX)
        if (path.startsWith("/auth") ||
            path.startsWith("/api") ||     // ✅ ADD THIS (CRITICAL FIX)
            path.startsWith("/oauth2") ||
            path.startsWith("/login/oauth2") ||
            path.startsWith("/favicon.ico")) {

            chain.doFilter(request, response);
            return;
        }

        try {
            String header = request.getHeader("Authorization");

            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                String email = jwtUtil.extractEmail(token);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(email, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            System.out.println("JWT ERROR: " + e.getMessage());
        }

        chain.doFilter(request, response);
    }
}