package com.example.base.repository;

import com.example.base.entity.Role;
import com.example.base.enumeration.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author HungDV
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleEnum roleName);

//    Role findByRoleName(RoleEnum roleName);
}
