package com.example.base.enumeration;

import com.example.base.constant.SecurityConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * @author HungDV
 * provider Role in this project
 */
@RequiredArgsConstructor
public enum RoleEnum {
    ADMIN(Set.of(
            PermissionEnum.ADMIN_CREATE_USER,
            PermissionEnum.ADMIN_UPDATE_USER,
            PermissionEnum.ADMIN_VIEW_USER_DETAILS,
            PermissionEnum.ADMIN_VIEW_ALL_USERS
    )), // admin có quyền: CRUD all
    USER(Set.of(PermissionEnum.USER_VIEW_USER_DETAILS)) // user có quyền: get user detail
    ;

    @Getter
    private final Set<PermissionEnum> permissions;

//    public List<SimpleGrantedAuthority> getAuthorities(){
//        var authorities = getPermissions()
//               .stream()
//               .map(
//                       permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name())
//               ).toList();
//        authorities.add(new SimpleGrantedAuthority(SecurityConstant.PREFIX_ROLE_NAME+this.name()));
//        return authorities;
//    }
}
