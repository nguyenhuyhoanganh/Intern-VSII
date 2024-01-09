package com.example.base.controller;

import com.example.base.dto.AuthDTO;
import com.example.base.dto.ResponseDTO;
import com.example.base.dto.TokenDTO;
import com.example.base.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HungDV
 * Class để test api với spring security jwt
 */

@RestController
@RequestMapping("/auth")
public class JwtProviderController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @Operation(summary = "Api kiểm tra xác thực và phân quyền với role USER",
            description = "Trả về dữ response body ếu thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @Operation(summary = "Api kiểm tra xác thực và phân quyền với role ADMIN",
            description = "Trả về dữ response body ếu thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @Operation(summary = "Phương thức để như tính năng login xác thực người dùng",
            description = "Trả về token nếu thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trả về token"),
            @ApiResponse(responseCode = "404", description = "Lỗi không tìm thấy người dùng với username vaf authenticationCode hợp lệ"),
    })
    @Parameters(@Parameter(name = "AuthDTO.class",description = "Gửi lên 2 trường username và pass"))
    @PostMapping("/generateToken")
    public ResponseDTO<TokenDTO> authenticateAndGetToken(@RequestBody AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getAuthenticationCode())
        );
        if (authentication.isAuthenticated()) {
            return ResponseDTO.<TokenDTO>builder().data(jwtService.generateToken(authDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
