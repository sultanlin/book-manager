package com.sultanlinjawi.library.security;

import com.sultanlinjawi.library.models.User;
import com.sultanlinjawi.library.repos.UserRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =
                userRepo.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new SecurityUserDetails(user);
    }
}
