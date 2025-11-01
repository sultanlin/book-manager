package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.AuthResponse;
import com.sultanlinjawi.library.dto.LoginRequest;
import com.sultanlinjawi.library.dto.RegisterRequest;
import com.sultanlinjawi.library.services.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                authService.login(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        var authResponse =
                authService.register(
                        registerRequest.getUsername(),
                        registerRequest.getPassword(),
                        registerRequest.getPasswordConfirmation());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}
