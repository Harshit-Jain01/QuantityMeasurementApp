package com.app.quantitymeasurement.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;

import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil util;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, JwtUtil util, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.util = util;
        this.passwordEncoder = passwordEncoder;
    }

    // ===============================
    // SIGNUP
    // ===============================
    public String create(User user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        // 🔐 HASH PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return util.generateToken(user.getEmail());
    }

    // ===============================
    // LOGIN
    // ===============================
    public String login(String email, String password) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔐 SECURE PASSWORD CHECK
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return util.generateToken(email);
    }
    
    
    public String googleLogin(String idTokenString) throws Exception {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance()        )
        .setAudience(Collections.singletonList("464577427173-7950va68opt82k71u9vefv7o9oe0eaks.apps.googleusercontent.com"))
        .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken == null) {
            throw new RuntimeException("Invalid Google token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();

        String email = payload.getEmail();
        String name = (String) payload.get("name");

        //  Check user
        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(""); // no password
            user.setProvider("GOOGLE");
            repository.save(user);
        }

        //  Generate JWT
        return util.generateToken(email);
    }
   
    
}