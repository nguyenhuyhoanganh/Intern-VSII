package com.example.base.config;

import com.example.base.entity.User;
import com.example.base.security.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAConfiguration {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> {
            // get user from security context
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            System.out.println(authentication);
//            if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//                return Optional.empty();
//            }
//            User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

            // set default user id 1L to created_by and modified_by
            User user = User.builder().id(1L).build();
            return Optional.ofNullable(user);
        };
    }
}
