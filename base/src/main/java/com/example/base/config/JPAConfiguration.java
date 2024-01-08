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

/**
 * @author AnhNHH.
 * Class cấu hình JPA Auditing.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAConfiguration {

    /**
     * Cung cấp một triển khai của AuditorAware cho Spring Data JPA.
     * Triển khai này nhận user từ security context cho các trường created_by và modified_by.
     * Cung cấp mặc định user với ID 1L để tạm thời, do chưa triển khai đầy đủ Spring Security.
     *
     * @return AuditorAware triển khai cho Spring Data JPA.
     */
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
            User user = new User();
            user.setId(1L);
            return Optional.ofNullable(user);
        };
    }
}
