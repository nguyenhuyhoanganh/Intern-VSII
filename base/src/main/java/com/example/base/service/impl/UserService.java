package com.example.base.service.impl;

import com.example.base.constant.UserConstant;
import com.example.base.entity.Address;
import com.example.base.entity.User;
import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.model.UserDTO;
import com.example.base.repository.UserRepository;
import com.example.base.service.IUserService;
import com.example.base.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtils userUtils;

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> userUtils.mapUserToUserDto(user)).toList();
    }

    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id).map(user -> userUtils.mapUserToUserDto(user)).orElseThrow(
                () -> new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND)
        );
    }

    @Override
    @Transactional
    public UserDTO handleInsert(UserDTO userDTO) {
        if (userDTO.getId() != null) {
            throw new RuntimeException(UserConstant.USER_MESSAGE_IS_NULL);
        }
        User user = userUtils.mapUserDtoToUser(userDTO);
        user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));
        return userUtils.mapUserToUserDto(userRepository.save(user));

    }

    @Override
    @Transactional
    public UserDTO handleUpdate(Long id, UserDTO userDTO) throws Exception {
        try {
            Optional<User> userUpdate = userRepository.findById(userDTO.getId());

            if (userUpdate.isEmpty()) {
                throw new RuntimeException(UserConstant.USER_MESSAGE_NOT_FOUND);
            } else {
                User user = userUpdate.get();

                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(user.getEmail());
                user.setPhoneNumber(userDTO.getPhoneNumber());
                user.setDateOfBirth(userDTO.getDateOfBirth());
                user.setAuthenticationCode(userDTO.getAuthenticationCode());
                return userUtils.mapUserToUserDto(user);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    @Override
    @Transactional
    public void deleteById(Long id) throws UserNotFoundException {
        if (id == null || !userRepository.existsById(id)) {
            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
