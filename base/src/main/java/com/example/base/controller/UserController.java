package com.example.base.controller;

import com.example.base.constant.UserConstant;
import com.example.base.enumeration.PermissionEnum;
import com.example.base.enumeration.RoleEnum;
import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.dto.ResponseDTO;
import com.example.base.dto.UserDTO;
import com.example.base.security.CustomUserDetails;
import com.example.base.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    private final String ROLE_ADMIN = RoleEnum.ADMIN.toString();

    // get all

    /**
     * Lấy tất danh sách User
     *
     * @return
     */
    @Operation(summary = "Lấy danhh sách tất cả người dùng",
            description = "Trả về danh sách USER nếu thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trả về danh sách ngời dùng"),
            @ApiResponse(responseCode = "403", description = "không có quyền"),
            @ApiResponse(responseCode = "400", description = "Sai đường dẫn truy cập"),
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseDTO<List<UserDTO>> getAll() {
        return ResponseDTO.<List<UserDTO>>builder()
                .data(userService.getAll())
                .build();
    }

    // get by id

    /**
     * @param id
     * @return
     */
    @Operation(summary = "Lấy User theo ID",
            description = "Trả về người dùng và thông tin message trạng thái")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trả về người dùng và ca thông tin khác"),
            @ApiResponse(responseCode = "404", description = "Lỗi không tìm thấy người dùng với id được gửi"),
    })
    @Parameters(@Parameter(name = "id", description = "ID của User"))
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<UserDTO> getById(@PathVariable Optional<Long> id) {
        // lấy người dùng đang đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        // danh sách roles và permission của người dùng
        List<RoleEnum> roles = customUserDetails.getUser().getRoles().stream().map(role -> role.getRoleName()).toList();
//        List<PermissionEnum> permissions = roles.;

        // kiểm tra
        if (roles.contains(RoleEnum.USER) && (!id.get().equals(customUserDetails.getUser().getId()))){
            throw new UserNotFoundException(UserConstant.USER_MESSAGE_NOT_FOUND+" id:"+id.get());
        }

        return ResponseDTO.<UserDTO>builder()
                .data(userService.getById(id.orElseThrow(() -> new UserNotFoundException("ID không hợp lệ"))))
                .message(null)
                .build();
    }

    // insert
    @Operation(summary = "Thêm User ",
            description = "Trả về người dùng và thông tin message trạng thái")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trả về người dùng và ca thông tin khác"),
            @ApiResponse(responseCode = "403", description = "không có quyền"),
            @ApiResponse(responseCode = "400", description = "Sai đường dẫn truy cập"),
            @ApiResponse(responseCode = "404", description = "Lỗi không thỏa mãn validate. Tra về message lỗi"),

    })

    @Parameters(@Parameter(name = "AuthDTO.class", description = "Gửi lên 2 trường username và pass"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<UserDTO> insertUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseDTO.<UserDTO>builder()
                .data(userService.handleInsert(userDTO))
                .code(HttpStatus.CREATED.value())
                .build();
    }

    // update
    @Operation(summary = "Update User theo ID",
            description = "Trả về người dùng và thông tin message trạng thái")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trả về người dùng và ca thông tin khác"),
            @ApiResponse(responseCode = "403", description = "không có quyền"),
            @ApiResponse(responseCode = "400", description = "Sai đường dẫn truy cập"),
            @ApiResponse(responseCode = "404", description = "Lỗi không tìm thấy người dùng hoặc không thỏa mãn validate với id được gửi"),
    })

    @Parameters({@Parameter(name = "UserDTO", description = "thông tin cần update của người dùng"),
            @Parameter(name = "id", description = "id của user câần update")})
//
//    @Parameters({@Parameter(name = "UserDTO",description = "thông tin cần update của người dùng"),
//            @Parameter(name = "id",description = "id của user cần update")})
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) throws Exception {
        return ResponseDTO.<UserDTO>builder()
                .data(userService.handleUpdate(id, userDTO))
                .code(HttpStatus.OK.value())
                .build();
    }

    // delete
    @Operation(summary = "Xóa User theo ID",
            description = "Trả về người dùng và thông tin message trạng thái")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trả về người dùng đã được xóa"),
            @ApiResponse(responseCode = "403", description = "không có quyền"),
            @ApiResponse(responseCode = "400", description = "Sai đường dẫn truy cập"),
    })
    @Parameters(@Parameter(name = "id", description = "id của user câần update"))
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public ResponseDTO<UserDTO> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseDTO.<UserDTO>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .build();
    }
}
