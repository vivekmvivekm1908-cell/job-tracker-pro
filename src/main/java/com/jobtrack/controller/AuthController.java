package com.jobtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobtrack.dto.LoginRequest;
import com.jobtrack.dto.RegisterRequest;
import com.jobtrack.entity.User;
import com.jobtrack.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Register User
    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        try {

            String token = authService.login(
                    request.getEmail(),
                    request.getPassword());

            return ResponseEntity.ok(token);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid Email ID or Password");
        }
    }
}