package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.AuthResponse;
import com.sultanlinjawi.library.dto.LoginRequest;
import com.sultanlinjawi.library.dto.RegisterRequest;
import com.sultanlinjawi.library.security.JwtService;
import com.sultanlinjawi.library.security.UserDetailsImpl;
import com.sultanlinjawi.library.services.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails =
                authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        String tokenVal = jwtService.generateToken(userDetails);
        AuthResponse authResponse =
                AuthResponse.builder().token(tokenVal).username(loginRequest.getUsername()).build();
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest)
            throws AuthenticationException {
        UserDetailsImpl userDetails = authService.register(registerRequest);
        String tokenVal = jwtService.generateToken(userDetails);
        AuthResponse authResponse =
                AuthResponse.builder()
                        .token(tokenVal)
                        .username(registerRequest.getUsername())
                        .build();

        return ResponseEntity.created(URI.create("/api/v1/users/" + userDetails.getId()))
                .body(authResponse);
    }
}
