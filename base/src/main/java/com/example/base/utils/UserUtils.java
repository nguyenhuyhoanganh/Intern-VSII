package com.example.base.utils;

import com.example.base.entity.User;
import com.example.base.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final ModelMapper modelMapper;

    public User mapUserDtoToUser(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }
    public UserDTO mapUserToUserDto(User user){
        return modelMapper.map(user,UserDTO.class);
    }
}
