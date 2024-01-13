package com.example.base.repository;

import com.example.base.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
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

    /**
     * Gọi procedure để lấy tất cả địa chỉ.
     * @return Danh sách Address chứa tất cả địa chỉ.
     */
    @Procedure(procedureName = "getAllAddress")
    List<Address> getAllAddress();

    /**
     * Gọi procedure để tìm địa chỉ dựa trên ID.
     *
     * @param id của địa chỉ cần tìm.
     * @return Optional chứa địa chỉ nếu có, nếu không trả về Optional rỗng.
     */
    @Procedure(procedureName = "getAllAddressById")
    Optional<Address> findAddressById(@Param("id") Long id);

    /**
     * Gọi procedure để tìm địa chỉ dựa trên ID người dùng.
     *
     * @param userId của người dùng để tìm địa chỉ.
     * @return Danh sách Address chứa địa chỉ của người dùng có ID đã chỉ định.
     */
    @Procedure(procedureName = "getAllAddressByUserId")
    List<Address> findAddressByUserId(@Param("userId") Long userId);

    /**
     * Gọi procedure để tạo mới địa chỉ.
     *
     * @param userId của người dùng cho địa chỉ mới.
     * @param line  Đường của địa chỉ mới.
     * @param ward     Phường/Xã của địa chỉ mới.
     * @param district Quận/Huyện của địa chỉ mới.
     * @param province Tỉnh/Thành phố của địa chỉ mới.
     * @return Optional chứa địa chỉ mới nếu tạo thành công, nếu không trả về Optional rỗng.
     */
    @Procedure(procedureName = "createAddressProcedure")
    Optional<Address> createAddress(
            @Param("p_userId") Long userId,
            @Param("p_line") String line,
            @Param("p_ward") String ward,
            @Param("p_district") String district,
            @Param("p_province") String province);

    /**
     * Gọi procedure để cập nhật địa chỉ.
     *
     * @param Id của địa chỉ cần cập nhật.
     * @param userId của người dùng cho địa chỉ cập nhật.
     * @param line Đường của địa chỉ cập nhật.
     * @param ward    Phường/Xã của địa chỉ cập nhật.
     * @param district Quận/Huyện  của địa chỉ cập nhật.
     * @param province Tỉnh/Thành phố của địa chỉ cập nhật.
     * @return Optional chứa địa chỉ đã cập nhật nếu thành công, nếu không trả về Optional rỗng.
     */
    @Procedure(procedureName = "updateAddressProcedure")
    Optional<Address> updateAddress(
            @Param("p_Id") Long Id,
            @Param("p_userId") Long userId,
            @Param("p_line") String line,
            @Param("p_ward") String ward,
            @Param("p_district") String district,
            @Param("p_province") String province);

    /**
     * Gọi procedure để xóa địa chỉ.
     *
     * @param id của địa chỉ cần xóa.
     */
    @Procedure(procedureName = "deleteAddressProcedure")
    void deleteAddressProcedure(
            @Param("p_addressId") Long id
    );

}
