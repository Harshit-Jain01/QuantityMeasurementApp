package com.app.quantitymeasurement.service;

import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil util;

    public AuthService(UserRepository repository, JwtUtil util) {
        this.repository = repository;
        this.util = util;
    }

    // ===============================
    // SIGNUP
    // ===============================
    public String create(User user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        repository.save(user);

        return util.generateToken(user.getEmail()); // ✅ generate token
    }

    // ===============================
    // LOGIN
    // ===============================
    public String login(String email, String password) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ⚠️ SIMPLE PASSWORD CHECK (no hashing yet)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return util.generateToken(email);
    }
}