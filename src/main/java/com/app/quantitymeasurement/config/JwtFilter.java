package com.app.quantitymeasurement.config;

import java.io.IOException;
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
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("JWT FILTER HIT: " + path);

        // Skip ONLY public routes (NOT /api)
        if (path.startsWith("/auth") ||
            path.startsWith("/oauth2") ||
            path.startsWith("/login") ||
            path.startsWith("/favicon.ico") ||
            path.startsWith("/error")) {

            chain.doFilter(request, response);
            return;
        }

        try {
            String header = request.getHeader("Authorization");

            //  If token missing → block request
            if (header == null || !header.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Missing Token\"}");
                return;
            }

            String token = header.substring(7);

            //  Validate & extract user
            String email = jwtUtil.extractEmail(token);
            System.out.println("JWT VALID USER: " + email);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            System.out.println("JWT ERROR: " + e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid Token\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}