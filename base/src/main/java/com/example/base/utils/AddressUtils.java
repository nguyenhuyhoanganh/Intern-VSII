package com.example.base.utils;

import com.example.base.entity.Address;
import com.example.base.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Là một class dùng để đổi giữa đối tượng Address và AddressDTO
 * @author Phuong Oanh
 */
@Component
@RequiredArgsConstructor
public class AddressUtils {
    private final ModelMapper modelMapper;
    /**
     * Chuyển đổi đối tượng AddressDTO thành  Address
     * @param addressDTO Thông tin địa chỉ đầu vào.
     * @return Đối tượng Address được chuyển đổi.
     */
    public Address mapAddressDtoToAddress(AddressDTO addressDTO){
        return modelMapper.map(addressDTO,Address.class);
    }
    /**
     * Chuyển đổi đối tượng Address thành  AddressDTO
     * @param address Đối tượng Address đầu vào.
     * @return Đối tượng AddressDTO được chuyển đổi.
     */
    public AddressDTO mapAddressToAddressDto(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }
}
