package com.app.quantitymeasurement.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("JWT FILTER HIT: " + path);

        // ✅ 1. Allow preflight requests (VERY IMPORTANT for CORS)
        if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            chain.doFilter(request, response);
            return;
        }

        // ✅ 2. Skip public routes
        if (path.startsWith("/auth") ||
            path.startsWith("/oauth2") ||
            path.startsWith("/login") ||
            path.startsWith("/error") ||
            path.contains("google")) {

            chain.doFilter(request, response);
            return;
        }

        try {
            String header = request.getHeader("Authorization");

            // ❌ No token → block
            if (header == null || !header.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Missing Token\"}");
                return;
            }

            String token = header.substring(7);

            // ✅ Extract user
            String email = jwtUtil.extractEmail(token);

            // ✅ Avoid resetting if already authenticated
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(email, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            System.out.println("JWT ERROR: " + e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid Token\"}");
            return;
        }

        // ✅ Continue request
        chain.doFilter(request, response);
    }
}