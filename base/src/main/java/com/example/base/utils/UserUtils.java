package com.example.base.utils;

import com.example.base.entity.User;
import com.example.base.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    public User mapUserDtoToUser(UserDTO userDTO){

        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .dateOfBirth(userDTO.getDateOfBirth())
                .build();
    }
    public UserDTO mapUserToUserDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
