package com.example.base.security;

import com.example.base.constant.UserConstant;
import com.example.base.entity.User;
import com.example.base.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author AnhNHH
 *
 * Class cung cấp triển khai tùy chỉnh cho interface UserDetailsService.
 * Ký hiện @Service đánh dấu CustomUserDetailsService là 1 Bean singleton được tạo mặc định cho Spring Security.
 */

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    /**
     * Ghi đè lại phương thức loadUserByUsername của UserDetailsService.
     * Gọi đến UserRepository trả lại User theo username
     * Tạo CustomUserDetails từ User tìm được
     *
     * @param username username của User.
     * @return CustomUserDetails các SimpleGrantedAuthority.
     * @throws UsernameNotFoundException nếu không tìm thấy User từ username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find user by username

        User user = repository.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException(UserConstant.USER_NOT_FOUND_BY_USERNAME + username));
        return new CustomUserDetails(user);
    }
}

