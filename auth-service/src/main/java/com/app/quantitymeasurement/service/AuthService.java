package com.app.quantitymeasurement.service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final UserRepository repository;
    private final JwtUtil util;
    private final PasswordEncoder passwordEncoder;
    
    @Value("${google.client-id}")
    private String clientIds;
    
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
        if (idTokenString == null || idTokenString.isBlank()) {
            throw new RuntimeException("Google ID token is null or empty");
        }

        try {
            List<String> audiences = Arrays.stream(clientIds.split(","))
                    .map(String::trim)
                    .filter(value -> !value.isEmpty())
                    .collect(Collectors.toList());

            if (audiences.isEmpty()) {
                throw new RuntimeException("Google client id is not configured");
            }

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
                    .setAudience(audiences)
                    .build();
            GoogleIdToken idToken = verifier.verify(idTokenString.trim());
            if (idToken == null) {
                throw new RuntimeException("Invalid Google ID token");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            Object emailVerified = payload.get("email_verified");

            if (email == null) {
                throw new RuntimeException("Email not found in token");
            }

            if (!Boolean.TRUE.equals(emailVerified)) {
                throw new RuntimeException("Google account email is not verified");
            }

            User user = repository.findByEmail(email).orElse(null);

            if (user == null) {
                user = new User();
                user.setEmail(email);
                user.setName(name != null ? name : email);
                user.setPassword("");
                user.setProvider("GOOGLE");
                repository.save(user);
            }

            return util.generateToken(email);

        } catch (RuntimeException e) {
            log.warn("Google login rejected: {}", e.getMessage());
            throw e;
        } catch (GeneralSecurityException | IOException e) {
            log.error("Google token verification failed", e);
            throw new RuntimeException("Google token verification failed: " + e.getClass().getSimpleName(), e);
        } catch (Exception e) {
            log.error("Google login error", e);
            throw new RuntimeException("Google login error: " + e.getMessage(), e);
        }
    }
}
