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
public enum RoleEnum {
    ADMIN, // admin có quyền: CRUD all
    USER // user có quyền: get user detail
    ;
}
