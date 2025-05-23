package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.AuthResponse;
import com.sultanlinjawi.library.models.User;
import com.sultanlinjawi.library.repos.UserRepo;
import com.sultanlinjawi.library.security.JwtService;
import com.sultanlinjawi.library.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepo userRepo;
    private final JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public AuthResponse register(String username, String password, String passwordConfirmation) {

        if (!password.equals(passwordConfirmation)) {
            throw new IllegalArgumentException("Password confirmation failed");
        }

        var user = createUser(username, password);

        var userDetails = new UserDetailsImpl(user);

        var tokenVal = jwtService.generateToken(userDetails);

        return AuthResponse.builder().token(tokenVal).username(username).build();
    }

    public AuthResponse login(String username, String password) {
        UserDetails userDetails = authenticate(username, password);
        String tokenVal = jwtService.generateToken(userDetails);
        AuthResponse authResponse =
                AuthResponse.builder().token(tokenVal).username(username).build();
        return authResponse;
    }

    public UserDetails authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        return userDetailsService.loadUserByUsername(username);
    }

    private User createUser(String username, String password) {

        Optional<User> existingUser = userRepo.findByUsername(username);

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username taken");
        }

        var user = User.builder().username(username).password(encoder.encode(password)).build();
        return userRepo.save(user);
    }
}
