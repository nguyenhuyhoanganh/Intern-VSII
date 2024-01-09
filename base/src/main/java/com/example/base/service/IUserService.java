package com.example.base.service;

import com.example.base.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();

    UserDTO getById(Long id);

    UserDTO handleInsert(UserDTO userDTO);

    UserDTO handleUpdate(Long id,UserDTO userDTO) throws Exception;

    void deleteById(Long id);
}
