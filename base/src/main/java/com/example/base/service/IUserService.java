package com.example.base.service;

import com.example.base.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAll();

    UserDTO getById(Long id);

    UserDTO handleInsert(UserDTO userDTO);

    UserDTO handleUpdate(Long id,UserDTO userDTO) throws Exception;

    void deleteById(Long id);
}
