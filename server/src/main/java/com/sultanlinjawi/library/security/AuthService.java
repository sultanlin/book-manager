package com.sultanlinjawi.library.security;

import com.sultanlinjawi.library.user.User;
import com.sultanlinjawi.library.user.UserRepo;

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
class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepo userRepo;
    private final PasswordEncoder pwEncoder;

    @Transactional
    AuthResponse register(String username, String password, String passwordConfirmation) {

        if (!password.equals(passwordConfirmation)) {
            throw new IllegalArgumentException("Password confirmation failed");
        }

        createUser(username, password);

        return login(username, password);
    }

    AuthResponse login(String username, String password) {
        var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password));
        var tokenVal = tokenService.generateToken(authentication);
        return new AuthResponse(username, tokenVal);
    }

    private User createUser(String username, String password) {

        if (userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("Username taken");
        }

        var user = User.builder().username(username).password(pwEncoder.encode(password)).build();
        return userRepo.save(user);
    }
    // TODO: add delete user and update password and maybe update username
}
