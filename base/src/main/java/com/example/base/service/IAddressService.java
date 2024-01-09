package com.example.base.service;

import com.example.base.dto.AddressDTO;

import java.util.List;

/**
 * @author Phuong Oanh
 */
public interface IAddressService {
    List<AddressDTO> getAll();

    AddressDTO findAddressById(Long id);

    List<AddressDTO> findAddressByUserId(Long idUser);

    AddressDTO createAddress(AddressDTO addressDTO);

    AddressDTO updateAddress(Long id,AddressDTO addressDTO);

    void deleteAddressById(Long id);
}
