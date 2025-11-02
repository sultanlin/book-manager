package com.sultanlinjawi.library.repos;

import com.sultanlinjawi.library.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

    Boolean existsByUsername(String username);
}
