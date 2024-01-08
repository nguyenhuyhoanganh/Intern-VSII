package com.example.base.service.impl;

import com.example.base.entity.Address;
import com.example.base.entity.User;
import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.model.AddressDTO;
import com.example.base.repository.AddressRepository;
import com.example.base.repository.UserRepository;
import com.example.base.service.IAddressService;
import com.example.base.utils.AddressUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Phuong Oanh
 */
@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;
    private final AddressUtils addressUtils;
    private final UserRepository userRepository;

    @Override
    public List<AddressDTO> getAll() {
        return addressRepository.findAll().stream()
                .map(address -> addressUtils.mapAddressToAddressDto(address))
                .toList();
    }

    @Override
    public List<AddressDTO> findAddressByUserId(Long idUser) {
        return addressRepository.findByUserId(idUser).stream()
                .map(address -> addressUtils.mapAddressToAddressDto(address))
                .toList();
    }

    @Transactional
    @Override
    public AddressDTO create(AddressDTO addressDTO) {
        User user = userRepository.findById(addressDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Không tìm thấy người dùng: "));

        Address newAddress = addressUtils.mapAddressDtoToAddress(addressDTO);
        newAddress.setUser(user);

        return addressUtils.mapAddressToAddressDto(addressRepository.save(newAddress));
    }

    @Transactional
    @Override
    public AddressDTO update(Long id, AddressDTO addressDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            Address updatedAddress = addressUtils.mapAddressDtoToAddress(addressDTO);
            updatedAddress.setId(id);
            updatedAddress.setUser(address.getUser());
            return addressUtils.mapAddressToAddressDto(addressRepository.save(updatedAddress));
        } else {
            throw new RuntimeException("Địa chỉ không tồn tại ");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Địa chỉ không tồn tại ");
        }
        addressRepository.delete(optional.get());
    }
}
