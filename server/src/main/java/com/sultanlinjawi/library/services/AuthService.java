package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.AuthResponse;
import com.sultanlinjawi.library.models.User;
import com.sultanlinjawi.library.repos.UserRepo;
import com.sultanlinjawi.library.security.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepo userRepo;
    private final PasswordEncoder pwEncoder;

    @Transactional
    public AuthResponse register(String username, String password, String passwordConfirmation) {

        if (!password.equals(passwordConfirmation)) {
            throw new IllegalArgumentException("Password confirmation failed");
        }

        createUser(username, password);

        return login(username, password);
    }

    public AuthResponse login(String username, String password) {
        var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password));
        var tokenVal = tokenService.generateToken(authentication);
        return new AuthResponse(tokenVal, username);
    }

    private User createUser(String username, String password) {

        if (userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("Username taken");
        }

        var user = User.builder().username(username).password(pwEncoder.encode(password)).build();
        return userRepo.save(user);
    }
}
