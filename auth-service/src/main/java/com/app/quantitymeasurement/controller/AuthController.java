package com.app.quantitymeasurement.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.AuthResponseDTO;
import com.app.quantitymeasurement.dto.SignInDto;
import com.app.quantitymeasurement.dto.SignUpDto;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@RequestBody SignUpDto dto) {

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setProvider("LOCAL");

        String token = service.create(user);

        return ResponseEntity.ok(
                new AuthResponseDTO(token, dto.getEmail(), "User registered successfully")
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody SignInDto dto) {
        try {
            String token = service.login(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(
                    new AuthResponseDTO(token, dto.getEmail(), "Login successful")
            );
        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body(new AuthResponseDTO(null, null, e.getMessage()));
        }
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponseDTO> googleLogin(@RequestBody Map<String, String> body) {
        try {
            String idToken = body.get("token");
            if (idToken == null || idToken.isBlank()) {
                idToken = body.get("credential");
            }

            if (idToken == null || idToken.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(new AuthResponseDTO(null, null, "Google ID token is missing"));
            }

            String jwt = service.googleLogin(idToken);

            return ResponseEntity.ok(
                    new AuthResponseDTO(jwt, null, "Google login success")
            );

        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body(new AuthResponseDTO(null, null, e.getMessage()));
        }
    }
}
