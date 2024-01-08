package com.example.base.controller;

import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.model.ResponseDTO;
import com.example.base.model.UserDTO;
import com.example.base.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Phuong Oanh
 * Class xử lý tiếp nhận request của User từ client
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    // get all

    /**
     * Lấy tất danh sách User
     * @return
     */
    @GetMapping
    public ResponseDTO<List<UserDTO>> getAll(){
        return ResponseDTO.<List<UserDTO>>builder()
                .data(userService.getAll())
                .build();
    }

    // get by id
    @GetMapping("{id}")
    public ResponseDTO<UserDTO> getById(@PathVariable Optional<Long> id) {
        return ResponseDTO.<UserDTO>builder()
                .data(userService.getById(id.orElseThrow( () -> new UserNotFoundException("ID không hợp lệ"))))
                .message(null)
                .build();
    }
    // insert
    @PostMapping
    public ResponseDTO<UserDTO> insertUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseDTO.<UserDTO>builder()
                .data(userService.handleInsert(userDTO))
                .code(HttpStatus.CREATED.value())
                .build();
    }
    // update
    @PutMapping("{id}")
    public ResponseDTO<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) throws Exception {
        return ResponseDTO.<UserDTO>builder()
                .data(userService.handleUpdate(id,userDTO))
                .code(HttpStatus.OK.value())
                .build();
    }
    // delete
    @DeleteMapping("{id}")
    public ResponseDTO<UserDTO> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseDTO.<UserDTO>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }
}
