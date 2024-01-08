package com.example.base.service;

import com.example.base.model.AddressDTO;

import java.util.List;

/**
 * @author Phuong Oanh
 */
public interface IAddressService {
    /**
     * Phương thức này dùng để trả về tất cả các danh sách địa chỉ
     * @return Danh sách địa chỉ dưới dạng danh sách các đối tượng AddressDTO.
     */
    List<AddressDTO> getAll();

    /**
     * Lấy danh sách các địa chỉ liên quan đến một người dùng cụ thể.
     * @param id của người dùng.
     * @return Danh sách địa chỉ liên quan đến người dùng có ID tương ứng.
     */

    List<AddressDTO> findAddressByUserId(Long idUser);
    /**
     * Tạo một địa chỉ mới dựa trên thông tin được cung cấp trong đối tượng AddressDTO.
     * @param addressDTO Thông tin địa chỉ mới.
     * @return Đối tượng AddressDTO của địa chỉ mới được tạo.
     */
    AddressDTO createAddress(AddressDTO addressDTO);


    /**
     * Cập nhật một địa chỉ hiện tại dựa trên ID và thông tin mới từ đối tượng AddressDTO.
     * @param id của địa chỉ cần cập nhật.
     * @param addressDTO Thông tin mới của địa chỉ.
     * @return Đối tượng AddressDTO sau khi được cập nhật.
     */
    AddressDTO updateAddress(Long id, AddressDTO addressDTO);

    /**
     * Xóa một địa chỉ dựa trên ID.
     * @param id của địa chỉ cần xóa.
     */
    void deleteById(Long id);
}
