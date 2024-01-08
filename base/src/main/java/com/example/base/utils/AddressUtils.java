package com.example.base.utils;

import com.example.base.entity.Address;
import com.example.base.entity.User;
import com.example.base.model.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Là một dùng để đổi giữa đối tượng Address và AddressDTO
 * @author Phuong Oanh
 */
@Component
@RequiredArgsConstructor
public class AddressUtils {
    private final ModelMapper modelMapper;

    /**
     * Chuyển đổi đối tượng {@code AddressDTO} thành {@code Address}.
     * @param addressDTO Thông tin địa chỉ đầu vào.
     * @return Đối tượng {@code Address} được chuyển đổi.
     */
    public Address mapAddressDtoToAddress(AddressDTO addressDTO){
        Address address = modelMapper.map(addressDTO, Address.class);
        if (addressDTO.getUserId() != null) {
            User user = modelMapper.map(addressDTO.getUserId(), User.class);
            address.setUser(user);
        }
        return address;
    }

    /**
     * Chuyển đổi đối tượng {@code Address} thành {@code AddressDTO}.
     * @param address Đối tượng {@code Address} đầu vào.
     * @return Đối tượng {@code AddressDTO} được chuyển đổi.
     */
    public AddressDTO mapAddressToAddressDto(Address address){
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);

        if (address.getUser() != null) {
            addressDTO.setUserId(address.getUser().getId());
        }
        return addressDTO;
    }

}
