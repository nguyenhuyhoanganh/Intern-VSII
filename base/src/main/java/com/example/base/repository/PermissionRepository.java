package com.example.base.repository;

import com.example.base.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HungDV
 */
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
