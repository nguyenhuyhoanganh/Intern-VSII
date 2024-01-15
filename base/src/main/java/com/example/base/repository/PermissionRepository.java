package com.example.base.repository;

import com.example.base.entity.Permission;
import com.example.base.enumeration.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HungDV
 */
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Permission findByName(PermissionEnum name);
}
