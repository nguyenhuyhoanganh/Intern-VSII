package com.example.base.security;

import com.example.base.constant.SecurityConstant;
import com.example.base.entity.Permission;
import com.example.base.entity.Role;
import com.example.base.enumeration.RoleEnum;
import com.example.base.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
import java.util.List;

/**
 * @author HungDV
 * <p>
 * Class cung cấp cấu hình chung cho Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends Exception {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PermissionRepository permissionRepository;

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
//        user chỉ xem users/** (get)

        http.csrf(AbstractHttpConfigurer::disable);
        // uri
        List<Permission> permissions = permissionRepository.findAll();
        for (Permission permission : permissions) {
            String role = permission.getPermissionName().name().substring(0, permission.getPermissionName().name().indexOf(SecurityConstant.INDEX_OF_));

            http.authorizeHttpRequests(req -> {
//                req.requestMatchers(
//                        permission.getUrl()
//                ).hasRole(role);

                req.requestMatchers(
                        HttpMethod.valueOf(permission.getMethod().toString()),
                        permission.getUrl()
                ).hasAuthority(role);
            });
        }

        //any request


        //sesion manager
        http.sessionManagement(
                httpSecuritySessionManagement -> httpSecuritySessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // provider
        http.authenticationProvider(authenticationProvider());

        //filter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(req -> {
            req.requestMatchers(HttpMethod.GET, SecurityConstant.PRIVATE_URIS_ROLE_USER)
                    .hasAnyRole(RoleEnum.USER.name(), RoleEnum.ADMIN.name());
            req.requestMatchers(SecurityConstant.PRIVATE_URIS_ROLE_ADMIN)
                    .hasAnyRole(RoleEnum.ADMIN.name());
            req.anyRequest()
                    .permitAll();
        });
        return http.build();
    }


    /**
     * Bean để dùng quản lý các người dùng đã đăng nhập
     *
     * @param config AuthenticationManager.class
     * @return AuthenticationManager object bean
     * @throws Exception
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


    ////                    req.requestMatchers(HttpMethod.GET,SecurityConstant.PRIVATE_URIS_ROLE_USER).hasAnyAuthority(RoleEnum.ROLE_USER.toString());
////                    req.requestMatchers(SecurityConstant.PRIVATE_URIS_ROLE_ADMIN).hasAuthority(RoleEnum.ROLE_ADMIN.toString());
//                    req.requestMatchers(HttpMethod.GET,"/users/*")
//                            .hasAnyRole(RoleEnum.USER.name(),RoleEnum.ADMIN.name());
//                    req.requestMatchers("/users","/users/**")
//                            .hasAnyRole(RoleEnum.ADMIN.name());
//                    req.anyRequest()
//                            .permitAll();
//    List<Role> roles = roleRepository.findAll();
//                    for (Role role : roles) {
//        for (Permission permission : role.getPermissions()) {
//            req.requestMatchers(permission.getMethod().toString(), permission.getUrl()).hasAnyRole(role.getRoleName().toString());
//        }
//    }
}