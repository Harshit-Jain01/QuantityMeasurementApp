package com.app.quantitymeasurement.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.quantitymeasurement.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // ✅ Get user from Google
        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        // ✅ Extract email
        String email = user.getAttribute("email");

        // ✅ Generate JWT token
        String token = jwtUtil.generateToken(email);

        // ✅ Return JSON response (NOT redirect)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = "{"
                + "\"token\":\"" + token + "\","
                + "\"email\":\"" + email + "\","
                + "\"message\":\"Google login successful\""
                + "}";

        response.getWriter().write(jsonResponse);
    }
}