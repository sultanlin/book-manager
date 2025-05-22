package com.sultanlinjawi.library.services;

import com.sultanlinjawi.library.models.User;
import com.sultanlinjawi.library.repos.UserRepo;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    // TODO: Add update user
    private final UserRepo userRepo;

    public User getUserById(int userId) {
        return userRepo.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found with id: " + userId));
    }

    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }
}
