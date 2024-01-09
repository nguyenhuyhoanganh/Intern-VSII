package com.example.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author AnhNHH
 *
 * Class cung cấp cấu hình chung cho Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends Exception{
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/*").permitAll()
                        .requestMatchers("/user/**").hasAuthority(("USER")).
                        requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/logon").loginProcessingUrl("/logon").usernameParameter("username")
                        .passwordParameter("password").defaultSuccessUrl("/admin", true))
                .logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"))
                .formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login").usernameParameter("username")
                        .passwordParameter("password").defaultSuccessUrl("/", true))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));
        return http.build();
    }
    /**
     * Tạo 1 Bean cho PasswordEncoder sử dụng BCryptPasswordEncoder.
     *
     * @return object BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Tạo 1 Bean cho AuthenticationManager.
     * Sử dụng AuthenticationManagerBuilder sử dụng các Bean UserDetailsService, PasswordEncoder đã tạo.
     *
     * @param http là object HttpSecurity để cung cấp các cấu hình cho Spring Security.
     * @param userDetailsService làm Bean CustomUserDetails được tiêm vào method.
     * @param passwordEncoder là Bean BCryptPasswordEncoder được tiêm vào method.
     * @return object AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).and().build();
    }

    /**
     * Tạo 1 Bean cho SecurityFilterChain.
     * Sử dụng SecurityFilterChain caung cấp cấu hình cho Spring Security.
     * Bao gồm về cors, crsf, j session, phân quyển endpoint, thêm filter...
     *
     * @param http là object HttpSecurity để cung cấp các cấu hình cho Spring Security.
     * @return object SecurityFilterChain.
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /* cors, csrf */
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
        /* j_session */
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /* matcher */
        http.authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
        );

        /* add filter */

        return http.build();
    }

    /**
     * Tạo 1 Bean cho CorsConfigurationSource.
     * Cung cấp cấu hình cho bảo mật Cors.
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