package com.example.base.service.impl;

import com.example.base.constant.UserConstant;
import com.example.base.entity.Role;
import com.example.base.entity.User;
import com.example.base.enumeration.RoleEnum;
import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.dto.UserDTO;
import com.example.base.repository.RoleRepository;
import com.example.base.repository.UserRepository;
import com.example.base.service.IUserService;
import com.example.base.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Class xử lý logic của User
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    // danh sách bean cần thiết
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtils userUtils;


    /**
     * Lấy danh sách User
     *
     * @return danh sách User
     */
    @Override
    public List<UserDTO> getAll() {
        return userRepository.sp_findAllUser().stream().map(user -> userUtils.mapUserToUserDto(user)).toList();
    }

    /**
     * Lấy user theo Id
     *
     * @param id String
     * @return UserDTO.class
     */
    @Override
    public UserDTO getById(Long id) {
        return userRepository.sp_findUserById(id).map(user -> userUtils.mapUserToUserDto(user)).orElseThrow(
                () -> new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND)
        );
    }

    /**
     * Xử lý khi insert User
     *
     * @param userDTO
     * @return userDTO
     */
//    @Override
//    @Transactional
//    public UserDTO handleInsert(UserDTO userDTO) {
//        if (userDTO.getId() != null) {
//            throw new RuntimeException(UserConstant.USER_MESSAGE_IS_NULL);
//        }
//
//        List<Role> roles = Arrays.asList(roleRepository.findByRoleName(RoleEnum.ROLE_USER).get());
//        User user = userUtils.mapUserDtoToUser(userDTO);
//        user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));
//        user.setRoles(roles);
//        return userUtils.mapUserToUserDto(userRepository.save(user));
//
//    }
    @Override
    @Transactional
    public UserDTO handleInsert(UserDTO userDTO) {
        if (userDTO.getId() != null) {
            throw new RuntimeException(UserConstant.USER_MESSAGE_IS_NULL);
        }

//        List<Role> roles = Arrays.asList(roleRepository.findByRoleName(RoleEnum.ROLE_USER).get());
//        User user = userUtils.mapUserDtoToUser(userDTO);
//        user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));
//        user.setRoles(roles);

        Optional<User> userCreated = userRepository.sp_createUser(
                userDTO.getDateOfBirth(),
                passwordEncoder.encode(userDTO.getAuthenticationCode()),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPhoneNumber(),
                userDTO.getUsername());
        return userUtils.mapUserToUserDto(userCreated.get());
    }

    /**
     * Xử lý khi update User
     *
     * @param id      id của user
     * @param userDTO user lâ từ req
     * @return User được update
     * @throws Exception
     */
//    @Override
//    @Transactional
//    public UserDTO handleUpdate(Long id, UserDTO userDTO)  {
//
//            Optional<User> userUpdate = userRepository.findById(id);
//
//            if (userUpdate.isEmpty()) {
//                throw new RuntimeException(UserConstant.USER_MESSAGE_NOT_FOUND);
//            } else {
//                User user = userUpdate.get();
//
//                user.setId(id);
//                user.setFirstName(userDTO.getFirstName());
//                user.setLastName(userDTO.getLastName());
//                user.setEmail(userDTO.getEmail());
//                user.setPhoneNumber(userDTO.getPhoneNumber());
//                user.setDateOfBirth(userDTO.getDateOfBirth());
//                user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));
//                return userUtils.mapUserToUserDto(user);
//            }
//
//    }
    @Override
    @Transactional
    public UserDTO handleUpdate(Long id, UserDTO userDTO) {


        if (id == null || !userRepository.existsById(id)) {
            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
        }
        // tham số của user đc thay đổi
        Optional<User> userUpdate = userRepository.sp_updateUser(
                id,
                userDTO.getDateOfBirth(),
                passwordEncoder.encode(userDTO.getAuthenticationCode()),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPhoneNumber(),
                userDTO.getUsername()
        );

        return userUtils.mapUserToUserDto(userUpdate.get());
    }

    /**
     * Xóa user by id
     *
     * @param id id của user cần xóa
     */
//    @Override
//    @Transactional
//    public void deleteById(Long id) {
//        if (id == null || !userRepository.existsById(id)) {
//            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
//        }
//        userRepository.deleteById(id);
//    }
    @Override
    public void deleteById(Long id) {
        if (id == null || !userRepository.existsById(id)) {
            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
        }
        userRepository.sp_deleteUserById(id);
    }
}
