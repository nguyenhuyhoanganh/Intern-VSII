package com.example.base.utils;

import com.example.base.constant.UserConstant;
import com.example.base.entity.User;
import com.example.base.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final ModelMapper modelMapper;

    /**
     * Chuyển và map từ kiểu userDTO sang User
     * @param userDTO
     * @return User.class
     */
    public User mapUserDtoToUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        return user;
    }

    /**
     * Chuyển và map từ kiểu User sang userDTO
     * @param user
     * @return UserDTO.class
     */
    public UserDTO mapUserToUserDto(User user){
        user.setAuthenticationCode(UserConstant.BLANK);
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);

        return userDTO;
    }
}
