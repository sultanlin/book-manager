package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.dto.RegisterRequest;
import com.sultanlinjawi.library.models.User;
import com.sultanlinjawi.library.repos.UserRepo;
import com.sultanlinjawi.library.security.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(
            UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager,
            UserRepo userRepo) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
    }

    public UserDetailsImpl register(RegisterRequest registerRequest) {

        Optional<User> user = userRepo.findByUsername(registerRequest.getUsername());

        if (user.isPresent()) {
            throw new IllegalArgumentException("Username is taken");
        }

        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirmation())) {
            throw new IllegalArgumentException("Password does not match password confirmation");
        }

        var newUser =
                User.builder()
                        .username(registerRequest.getUsername())
                        .password(encoder.encode(registerRequest.getPassword()))
                        .build();
        userRepo.save(newUser);
        return new UserDetailsImpl(newUser);
    }

    public UserDetails authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        return userDetailsService.loadUserByUsername(username);
    }
}
