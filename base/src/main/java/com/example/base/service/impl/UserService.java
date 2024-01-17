package com.example.base.service.impl;

import com.example.base.constant.UserConstant;
import com.example.base.entity.Role;
import com.example.base.entity.User;
import com.example.base.enumeration.RoleEnum;
import com.example.base.exception.domain.IdNotMatchException;
import com.example.base.exception.domain.UserAgeNotValidate;
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
//    @Override
//    public UserDTO getById(Long id) {
//        return userRepository.sp_findUserById(id).map(user -> userUtils.mapUserToUserDto(user)).orElseThrow(
//                () -> new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND)
//        );
//    }
    public UserDTO getById(Long id) {
        return userRepository.findById(id).map(user -> userUtils.mapUserToUserDto(user)).orElseThrow(
                () -> new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND)
        );
    }

    /**
     * Xử lý khi insert User
     *
     * @param userDTO thông tin user muốn khởi tạo
     * @return userDTO thông tin user đã đự cập nhật
     */
    @Override
    @Transactional
    public UserDTO handleInsert(UserDTO userDTO) {
        if (userUtils.validateUser(userDTO)) {
            if (userDTO.getId() != null) {
                throw new RuntimeException(UserConstant.USER_MESSAGE_IS_NULL);
            }
            List<Role> roles = Arrays.asList(roleRepository.findByRoleName(RoleEnum.USER).get());
            User user = userUtils.mapUserDtoToUser(userDTO);
            user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));
            user.setRoles(roles);
            return userUtils.mapUserToUserDto(userRepository.save(user));
        }
        throw new UserAgeNotValidate(UserConstant.AGE_NOT_VALID);
    }
    /**
     * Xử lý khi update User
     *
     * @param id của user cần update
     * @param userDTO thông tin user cần sửa
     * @return User thông tin user đã được update
     */
    @Override
    @Transactional
    public UserDTO handleUpdate(Long id, UserDTO userDTO) {
        if(userUtils.validateUser(userDTO)){
            if (id != userDTO.getId()) {
                throw new IdNotMatchException(UserConstant.ID_NOT_MATCH);
            }
            if (id == null || !userRepository.existsById(id)) {
                throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
            }
            Optional<User> userUpdate = userRepository.findById(id);
            User user = userUpdate.get();
            user.setId(id);
            user.setUsername(userDTO.getUsername());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setDateOfBirth(userDTO.getDateOfBirth());
            user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));

            List<Role> updatedRoles = userUtils.mapRoles(userDTO.getRoles());
            for (Role role : updatedRoles) {
                if (!roleRepository.existsById(role.getId())) {
                    throw new UserNotFoundException(UserConstant.ROLE_NOT_FOUND);
                }
            }
            user.setRoles(updatedRoles);
            userRepository.save(user);
            return userUtils.mapUserToUserDto(user);
        }
        throw new UserAgeNotValidate(UserConstant.AGE_NOT_VALID);
    }

//    @Override
//    @Transactional
//    public UserDTO handleUpdate(Long id, UserDTO userDTO) {
//
//
//        if (id != userDTO.getId()){
//            throw new IdNotMatchException(UserConstant.ID_NOT_MATCH);
//        }
//        if (id == null || !userRepository.existsById(id)) {
//            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
//        }
//        // tham số của user đc thay đổi
//        Optional<User> userUpdate = userRepository.sp_updateUser(
//                id,
//                userDTO.getDateOfBirth(),
//                passwordEncoder.encode(userDTO.getAuthenticationCode()),
//                userDTO.getEmail(),
//                userDTO.getFirstName(),
//                userDTO.getLastName(),
//                userDTO.getPhoneNumber(),
//                userDTO.getUsername()
//        );
//
//        return userUtils.mapUserToUserDto(userUpdate.get());
//    }


//    @Override
//    @Transactional
//    public UserDTO handleUpdate(Long id, UserDTO userDTO) {
//        Optional<User> userUpdate = userRepository.findById(id);
//
//        if (userUpdate.isPresent()) {
//            User user = userUpdate.get();
//            user.setId(id);
//            user.setUsername(userDTO.getUsername());
//            user.setFirstName(userDTO.getFirstName());
//            user.setLastName(userDTO.getLastName());
//            user.setEmail(userDTO.getEmail());
//            user.setPhoneNumber(userDTO.getPhoneNumber());
//            user.setDateOfBirth(userDTO.getDateOfBirth());
//            user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));
//
//            List<Role> updatedRoles = userUtils.mapRoles(userDTO.getRoles());
//            for (Role role : updatedRoles) {
//                if (!roleRepository.existsById(role.getId())) {
//                    throw new UserNotFoundException(UserConstant.ROLE_NOT_FOUND);
//                }
//            }
//            user.setRoles(updatedRoles);
//
//            userRepository.save(user);
//            return userUtils.mapUserToUserDto(user);
//        } else {
//            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
//        }
//    }
//        if (userUpdate.isEmpty()) {
//            throw new RuntimeException(UserConstant.USER_MESSAGE_NOT_FOUND);
//        } else {
//            User user = userUpdate.get();
//
//            user.setId(id);
//            user.setFirstName(userDTO.getFirstName());
//            user.setLastName(userDTO.getLastName());
//            user.setEmail(userDTO.getEmail());
//            user.setPhoneNumber(userDTO.getPhoneNumber());
//            user.setDateOfBirth(userDTO.getDateOfBirth());
//            user.setAuthenticationCode(passwordEncoder.encode(userDTO.getAuthenticationCode()));
//            return userUtils.mapUserToUserDto(user);
//        }


    /**
     * Xóa user by id
     *
     * @param id id của user cần xóa
     */
    @Override
    public void deleteById(Long id) {
        if (id == null || !userRepository.existsById(id)) {
            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
        }
        userRepository.sp_deleteUserById(id);
    }
//    @Override
//    @Transactional
//    public void deleteById(Long id) {
//        if (id == null || !userRepository.existsById(id)) {
//            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND);
//        }
//        userRepository.deleteById(id);
//    }
}
