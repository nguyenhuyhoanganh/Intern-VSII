package com.example.base.utils;

import com.example.base.entity.Address;
import com.example.base.entity.User;
import com.example.base.model.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Phuong Oanh
 */
@Component
@RequiredArgsConstructor
public class AddressUtils {
    private final ModelMapper modelMapper;
    public Address mapAddressDtoToAddress(AddressDTO addressDTO){
        Address address = modelMapper.map(addressDTO, Address.class);

        if (addressDTO.getUserId() != null) {
            User user = modelMapper.map(addressDTO.getUserId(), User.class);
            address.setUser(user);
        }
        return address;
    }
    public AddressDTO mapAddressToAddressDto(Address address){
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);

        if (address.getUser() != null) {
            addressDTO.setUserId(address.getUser().getId());
        }
        return addressDTO;
    }

}
