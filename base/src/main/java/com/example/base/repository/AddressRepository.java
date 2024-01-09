package com.example.base.repository;

import com.example.base.entity.Address;
import com.example.base.model.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
