package com.example.base.security;

import com.example.base.enumeration.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author AnhNHH
 * <p>
 * Class cung cấp cấu hình chung cho Spring Security.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * Tạo 1 Bean cho SecurityFilterChain.
     * Sử dụng SecurityFilterChain caung cấp cấu hình cho Spring Security.
     * Bao gồm về cors, crsf, j session, phân quyển endpoint, thêm filter...
     *
     * @param http là object HttpSecurity để cung cấp các cấu hình cho Spring Security.
     * @return object SecurityFilterChain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/auth/user/**").hasAuthority(RoleEnum.ROLE_USER.toString());
                    req.requestMatchers("/auth/admin/**").hasAuthority(RoleEnum.ROLE_ADMIN.toString());
                    req.anyRequest().permitAll();

                })
                .sessionManagement(
                        httpSecuritySessionManagement -> httpSecuritySessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }



    /**
     * Tạo 1 Bean cho AuthenticationManager.
     * Sử dụng AuthenticationManagerBuilder sử dụng các Bean UserDetailsService, PasswordEncoder đã tạo.
     * <p>
     * //     * @param http là object HttpSecurity để cung cấp các cấu hình cho Spring Security.
     * //     * @param userDetailsService làm Bean CustomUserDetails được tiêm vào method.
     * //     * @param passwordEncoder là Bean BCryptPasswordEncoder được tiêm vào method.
     *
     * @return object AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
    // User Creation

    /**
     * Tạo 1 Bean cho CorsConfigurationSource cung cấp cấu hình cho bảo mật Cors.
     * Đặt tạm thời cho phép truy cập endpoint từ mọi nguồn gốc, mọi method, mọi header.
     *
     * @return object CorsConfigurationSource.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
