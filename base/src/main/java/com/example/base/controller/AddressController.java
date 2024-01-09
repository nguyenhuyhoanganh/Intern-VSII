package com.example.base.controller;

import com.example.base.entity.Address;
import com.example.base.dto.AddressDTO;
import com.example.base.dto.ResponseDTO;
import com.example.base.service.impl.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Phuong Oanh
 * Class xử lý tiếp nhận request với Address từ client
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    /**
     * Xử lý request GET để lấy tất cả các địa chỉ có trong hệ thống.
     * @return ResponseDTO chứa danh sách địa chỉ và mã trạng thái HTTP.
     */
    @Operation(description = "Xem danh sách địa chỉ")
    @GetMapping("")
    public ResponseDTO<List<AddressDTO>> getAll() {
        return ResponseDTO.<List<AddressDTO>>builder()
                .data(addressService.getAll())
                .build();
    }
    /**
     * Xử lý request GET để lấy thông tin của một địa chỉ dựa trên ID cụ thể
     * @param id  của địa chỉ cần lấy thông tin.
     * @return ResponseDTO chứa dữ liệu địa chỉ và mã trạng thái HTTP.
     */
    @Operation(description = "Xem thông tin địa chỉ")
    @GetMapping("{id}")
    public ResponseDTO<AddressDTO> getById(@PathVariable("id") Long id) {
        return ResponseDTO.<AddressDTO>builder()
                .data(addressService.findAddressById(id))
                .code(HttpStatus.OK.value())
                .build();
    }

    /**
     * Xử lý request GET để xem danh sách địa chỉ của một người dùng.
     * @param idUser của người dùng.
     * @return ResponseDTO chứa danh sách địa chỉ và mã trạng thái HTTP.
     */
    @Operation(description = "Xem thông tin địa chỉ")
    @GetMapping("/user/{idUser}")
    public ResponseDTO<List<AddressDTO>> getByUserId(@PathVariable("idUser") Long idUser) {
        return ResponseDTO.<List<AddressDTO>>builder().
                data(addressService.findAddressByUserId(idUser)).build();
    }

    /**
     * Xử lý request POST để tạo mới một địa chỉ.
     * @param addressDTO Thông tin địa chỉ mới.
     * @return ResponseDTO chứa thông tin địa chỉ mới và mã trạng thái HTTP.
     */
    @Operation(description = "Tạo địa chỉ")
    @PostMapping("/add")
    public ResponseDTO<AddressDTO> create(@Valid @RequestBody AddressDTO addressDTO) {
        return ResponseDTO.<AddressDTO>builder()
                .data(addressService.createAddress(addressDTO))
                .code(HttpStatus.CREATED.value())
                .build();

    }

    /**
     * Xử lý request PUT để cập nhật một địa chỉ dựa trên ID.
     * @param addressDTO Thông tin địa chỉ mới.
     * @param id của địa chỉ cần cập nhật.
     * @return ResponseDTO chứa thông tin địa chỉ sau khi cập nhật và mã trạng thái HTTP.
     */
    @Operation(description = "Sửa địa chỉ")
    @PutMapping("/update/{id}")
    public ResponseDTO<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO addressDTO, @PathVariable Long id) {
        return ResponseDTO.<AddressDTO>builder()
                .data(addressService.updateAddress(id, addressDTO))
                .code(HttpStatus.OK.value())
                .build();
    }

    /**
     * Xử lý request DELETE để xóa một địa chỉ dựa trên id
     * @param id của địa chỉ cần xóa.
     * @return ResponseDTO chứa mã trạng thái HTTP sau khi xóa.
     */
    @Operation(description = "Xóa địa chỉ")
    @DeleteMapping("/{id}")
    public ResponseDTO<Address> deleteById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return ResponseDTO.<Address>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }
}
