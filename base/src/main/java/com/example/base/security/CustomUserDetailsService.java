package com.example.base.security;

import com.example.base.entity.User;
import com.example.base.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find user by username
        User user = repository.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}

