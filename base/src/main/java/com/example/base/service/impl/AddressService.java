package com.example.base.service.impl;

import com.example.base.constant.AddressConstant;
import com.example.base.entity.Address;
import com.example.base.entity.User;
import com.example.base.exception.domain.AddressNotFoundException;
import com.example.base.dto.AddressDTO;
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
 * Nó triển khai các phương thức từ IAddressService để thực hiện các thao tác như lấy danh sách địa chỉ,
 * tìm địa chỉ theo ID người dùng, tạo mới địa chỉ, cập nhật địa chỉ, và xóa địa chỉ.
 * @author Phuong Oanh
 */
@Transactional
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
//        return addressRepository.findAll().stream()
//                .map(address -> addressUtils.mapAddressToAddressDto(address))
//                .toList();
        return addressRepository.getAllAddress().stream()
                .map(address -> addressUtils.mapAddressToAddressDto(address))
                .toList();
    }
    /**
     * Phương thức này trả về một AddressDTO chứa thông tin của một địa chỉ dựa trên ID cụ thể
     * @param id của địa chỉ cần tìm kiếm.
     * @return Đối tượng AddressDTO chứa thông tin của địa chỉ.
     * @throws AddressNotFoundException Nếu không tìm thấy địa chỉ.
     */
    @Override
    public AddressDTO findAddressById(Long id) {
//        return addressRepository.findById(id).map(address -> addressUtils.mapAddressToAddressDto(address)).orElseThrow(
//                () -> new AddressNotFoundException(AddressConstant.ADDRESS_NOT_FOUND)
//        );
        return addressRepository.findAddressById(id).map(address -> addressUtils.mapAddressToAddressDto(address)).orElseThrow(
                () -> new AddressNotFoundException(AddressConstant.ADDRESS_NOT_FOUND)
        );
    }

    /**
     * Phương thức này trả về danh sách các địa chỉ liên quan đến một người dùng cụ thể dựa trên ID người dùng.
     * @param idUser của người dùng.
     * @return Danh sách địa chỉ liên quan đến người dùng có ID tương ứng.
     */
    @Override
    public List<AddressDTO> findAddressByUserId(Long idUser) {
//        Optional<User> optionalUser = userRepository.findById(idUser);
//        if (optionalUser.isPresent()) {
//            return addressRepository.findByUserId(idUser).stream()
//                    .map(address -> addressUtils.mapAddressToAddressDto(address))
//                    .toList();
//        } else {
//            throw new AddressNotFoundException(AddressConstant.USER_NOT_FOUND);
//        }
        List<Address> addresses = addressRepository.findAddressByUserId(idUser);
        if (!addresses.isEmpty()) {
            return addresses.stream()
                    .map(address -> addressUtils.mapAddressToAddressDto(address))
                    .toList();
        } else {
            throw new AddressNotFoundException(AddressConstant.USER_NOT_FOUND);
        }
    }

    /**
     * Phương thức này tạo một địa chỉ mới dựa trên thông tin được cung cấp trong đối tượng AddressDTO.
     * Trước khi tạo mới địa chỉ , nếu người dùng không tồn tại -> ném ra 1 ngoại lệ AddressNotFoundException với thông báo lỗi
     * Nếu người dùng tồn tại , thêm địa chỉ mới -> lưu vào db
     * @param addressDTO Thông tin địa chỉ mới.
     * @return Đối tượng AddressDTO của địa chỉ mới được tạo.
     * @throws RuntimeException Nếu không tìm thấy người dùng với ID tương ứng.
     */
    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
//        User user = userRepository.findById(addressDTO.getUserId())
//                .orElseThrow(() -> new AddressNotFoundException(AddressConstant.USER_NOT_FOUND));
//        Address newAddress = addressUtils.mapAddressDtoToAddress(addressDTO);
//        newAddress.setUser(user);
//        return addressUtils.mapAddressToAddressDto(addressRepository.save(newAddress));
        if (addressDTO.getId() != null) {
            throw new AddressNotFoundException(AddressConstant.ADDRESS_IS_NULL);
        }
        if (addressDTO.getUserId() == null || !userRepository.existsById(addressDTO.getUserId())) {
            throw new AddressNotFoundException(AddressConstant.USER_NOT_FOUND);
        }
        Optional<Address> optionalAddress = addressRepository.createAddress(
                addressDTO.getUserId(),
                addressDTO.getLine(),
                addressDTO.getDistrict(),
                addressDTO.getProvince(),
                addressDTO.getWard());
        return addressUtils.mapAddressToAddressDto(optionalAddress.get());
    }

    /**
     * Phương thức này cập nhật một địa chỉ hiện tại dựa trên ID và thông tin mới từ đối tượng AddressDTO.
     * @param id của địa chỉ cần cập nhật.
     * @param addressDTO Thông tin mới của địa chỉ.
     * @return Đối tượng AddressDTO sau khi được cập nhật.
     * @throws AddressNotFoundException Nếu không tìm thấy địa chỉ hoặc người dùng tương ứng.
     */

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
//        Optional<Address> optionalAddress = addressRepository.findById(id);
//        Optional<User> optionalUser = userRepository.findById(addressDTO.getUserId());
//
//        if (optionalAddress.isPresent() && optionalUser.isPresent()) {
//            Address address = optionalAddress.get();
//            Address updatedAddress = addressUtils.mapAddressDtoToAddress(addressDTO);
//            updatedAddress.setId(id);
//            updatedAddress.setUser(address.getUser());
//            return addressUtils.mapAddressToAddressDto(addressRepository.save(updatedAddress));
//        } else {
//            throw new AddressNotFoundException(AddressConstant.ADDRESS_NOT_FOUND);
//        }
        if (id != addressDTO.getId()) {
            throw new AddressNotFoundException(AddressConstant.ADDRESS_NOT_MATCH);
        }
        if (addressDTO.getUserId() == null || !userRepository.existsById(addressDTO.getUserId())) {
            throw new AddressNotFoundException(AddressConstant.USER_NOT_FOUND);
        }
        Optional<Address> optionalAddress = addressRepository.updateAddress(
                id,
                addressDTO.getUserId(),
                addressDTO.getLine(),
                addressDTO.getDistrict(),
                addressDTO.getProvince(),
                addressDTO.getWard());
        return addressUtils.mapAddressToAddressDto(optionalAddress.get());
    }

    /**
     * Phương thức này xóa một địa chỉ dựa trên ID.
     * @param id của địa chỉ cần xóa.
     * @throws AddressNotFoundException Nếu địa chỉ không tồn tại.
     */
    @Override
    public void deleteAddressById(Long id) {
//        Optional<Address> optional = addressRepository.findById(id);
//        if (!optional.isPresent()) {
//            throw new AddressNotFoundException(AddressConstant.ADDRESS_NOT_FOUND);
//        }
//        addressRepository.delete(optional.get());
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()) {
            throw new AddressNotFoundException(AddressConstant.ADDRESS_NOT_FOUND);
        }
        addressRepository.deleteAddressProcedure(id);
    }
}
