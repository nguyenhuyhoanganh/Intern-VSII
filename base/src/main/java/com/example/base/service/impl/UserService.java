package com.example.base.service.impl;

import com.example.base.entity.User;
import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.model.UserDTO;
import com.example.base.repository.UserRepository;
import com.example.base.service.IUserService;
import com.example.base.utils.UserUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final String MESSAGE_NOT_FOUND = "Người dùng không tồn tại";

    private final UserRepository userRepository;
    private final UserUtils userUtils;

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> userUtils.mapUserToUserDto(user)).toList();
    }

    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id).map(user -> userUtils.mapUserToUserDto(user)).orElseThrow(
                () -> new UserNotFoundException(MESSAGE_NOT_FOUND)
        );
    }

    @Override
    @Transactional
    public UserDTO handleInsert(UserDTO userDTO) {
            if (userDTO.getId() != null){
                throw new RuntimeException("Lỗi rồi kìa truyền id làm gì ???");
            }
            User user = userUtils.mapUserDtoToUser(userDTO);
            return userUtils.mapUserToUserDto(userRepository.save(user));

    }

    @Override
    @Transactional
    public UserDTO handleUpdate(Long id, Optional<UserDTO> userDTO) {
        if (id == null || userDTO.isEmpty()){
            throw new RuntimeException("Update thất bại vui lòng xem lại");
        }
        User user = userUtils.mapUserDtoToUser(userDTO.get());
        user.setId(id);
        user = userRepository.save(user);
        return userUtils.mapUserToUserDto(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws UserNotFoundException{
        if (id == null || !userRepository.existsById(id)){
            throw new UserNotFoundException(MESSAGE_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
