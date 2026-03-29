package com.app.quantitymeasurement.controller;

import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.AuthResponseDTO;
import com.app.quantitymeasurement.dto.SignInDto;
import com.app.quantitymeasurement.dto.SignUpDto;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // ✅ VERY IMPORTANT (frontend connection)
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // ===============================
    // SIGNUP
    // ===============================
    @PostMapping("/signup")
    public AuthResponseDTO signup(@RequestBody SignUpDto dto) {

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setProvider("LOCAL");

        String token = service.create(user);

        return new AuthResponseDTO(
                token,
                dto.getEmail(),
                "User registered successfully"
        );
    }

    // ===============================
    // LOGIN
    // ===============================
    @PostMapping("/signin")
    public AuthResponseDTO login(@RequestBody SignInDto dto) {

        String token = service.login(dto.getEmail(), dto.getPassword());

        return new AuthResponseDTO(
                token,
                dto.getEmail(),
                "Login successful"
        );
    }
}