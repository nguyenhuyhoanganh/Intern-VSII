package com.example.base.repository;

import com.example.base.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Phuong Oanh
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    /**
     * Tìm kiếm danh sách các địa chỉ dựa trên ID người dùng.
     * @param userId của người dùng.
     * @return Danh sách các địa chỉ liên quan đến người dùng có ID tương ứng.
     */
    List<Address> findByUserId(Long userId);


}
