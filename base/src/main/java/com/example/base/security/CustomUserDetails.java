package com.example.base.security;

import com.example.base.constant.SecurityConstant;
import com.example.base.entity.Role;
import com.example.base.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AnhNHH
 *
 * Class cung cấp triển khai tùy chỉnh cho interface UserDetails.
 */
@Data
public class CustomUserDetails implements UserDetails {

    private User user;// thằng này sẽ được lưu trong SecurityContext

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Ghi đè lại phương thức getAuthorities của UserDetails.
     *
     * @return 1 Collection các SimpleGrantedAuthority.
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getSimpleGrantedAuthorities();
    }

    private Collection<? extends GrantedAuthority> getSimpleGrantedAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.user.getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(SecurityConstant.PREFIX_ROLE_NAME+role.getRoleName().name()));
                    role.getPermissions().forEach(permissionEnum -> authorities.add(new SimpleGrantedAuthority(permissionEnum.getPermissionName().name())));
                }
        );
        this.user.getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(SecurityConstant.PREFIX_ROLE_NAME+role.getRoleName().name()));
                }
        );
        return authorities;
    }

    /**
     * Ghi đè lại phương thức getPassword của UserDetails.
     *
     * @return password của user.
     */
    @Override
    public String getPassword() {
        return user.getAuthenticationCode();
    }

    /**
     * Ghi đè lại phương thức getUsername của UserDetails.
     *
     * @return username của user.
     */
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }


    /**
     * Ghi đè lại phương thức isAccountNonExpired của UserDetails.
     *
     * @return true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Ghi đè lại phương thức isAccountNonLocked của UserDetails.
     *
     * @return true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Ghi đè lại phương thức isCredentialsNonExpired của UserDetails.
     *
     * @return true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Ghi đè lại phương thức isEnabled của UserDetails.
     *
     * @return true.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

