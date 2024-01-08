package com.example.base.service;

import com.example.base.model.AddressDTO;
import com.example.base.model.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Phuong Oanh
 */
public interface IAddressService {
    List<AddressDTO> getAll();

    List<AddressDTO> findAddressByUserId(Long idUser);

    AddressDTO create(AddressDTO addressDTO);

    AddressDTO update(Long id,AddressDTO addressDTO);

    void deleteById(Long id);
}
