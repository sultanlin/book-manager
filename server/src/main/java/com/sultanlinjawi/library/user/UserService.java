package com.sultanlinjawi.library.user;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    // TODO: Add update user
    private final UserRepo userRepo;

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Username not found"));
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        userRepo.deleteByUsername(username);
    }
}
