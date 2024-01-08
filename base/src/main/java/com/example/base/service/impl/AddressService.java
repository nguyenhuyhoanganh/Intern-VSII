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
 * Nó triển khai các phương thức từ {@code IAddressService} để thực hiện các thao tác như lấy danh sách địa chỉ,
 * tìm địa chỉ theo ID người dùng, tạo mới địa chỉ, cập nhật địa chỉ, và xóa địa chỉ.
 * @author Phuong Oanh
 */
@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;
    private final AddressUtils addressUtils;
    private final UserRepository userRepository;

    /**
     * Phương thức này trả về danh sách tất cả các địa chỉ trong hệ thống
     * @return Danh sách địa chỉ dưới dạng danh sách các đối tượng AddressDTO.
     */
    @Override
    public List<AddressDTO> getAll() {
        return addressRepository.findAll().stream()
                .map(address -> addressUtils.mapAddressToAddressDto(address))
                .toList();
    }
    /**
     * Phương thức này trả về danh sách các địa chỉ liên quan đến một người dùng cụ thể dựa trên ID người dùng.
     * @param idUser của người dùng.
     * @return Danh sách địa chỉ liên quan đến người dùng có ID tương ứng.
     */
    @Override
    public List<AddressDTO> findAddressByUserId(Long idUser) {
        return addressRepository.findByUserId(idUser).stream()
                .map(address -> addressUtils.mapAddressToAddressDto(address))
                .toList();
    }
    /**
     * Phương thức này tạo một địa chỉ mới dựa trên thông tin được cung cấp trong đối tượng AddressDTO.
     * @param addressDTO Thông tin địa chỉ mới.
     * @return Đối tượng AddressDTO của địa chỉ mới được tạo.
     * @throws RuntimeException Nếu không tìm thấy người dùng với ID tương ứng.
     */
    @Transactional
    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        User user = userRepository.findById(addressDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        Address newAddress = addressUtils.mapAddressDtoToAddress(addressDTO);
        newAddress.setUser(user);

        return addressUtils.mapAddressToAddressDto(addressRepository.save(newAddress));
    }
    /**
     * Phương thức này cập nhật một địa chỉ hiện tại dựa trên ID và thông tin mới từ đối tượng AddressDTO.
     * @param id của địa chỉ cần cập nhật.
     * @param addressDTO Thông tin mới của địa chỉ.
     * @return Đối tượng AddressDTO sau khi được cập nhật.
     * @throws RuntimeException Nếu địa chỉ không tồn tại.
     */
    @Transactional
    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
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

    /**
     * Phương thức này xóa một địa chỉ dựa trên ID.
     * @param id của địa chỉ cần xóa.
     * @throws RuntimeException Nếu địa chỉ không tồn tại.
     */
    @Override
    public void deleteById(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Địa chỉ không tồn tại ");
        }
        addressRepository.delete(optional.get());
    }
}
