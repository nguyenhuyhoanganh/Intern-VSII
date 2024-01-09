package com.example.base.model;

import com.example.base.constant.DateTimeConstant;
import com.example.base.constant.UserConstant;
import com.example.base.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @NotBlank( message = UserConstant.FIRST_NAME_NOT_BLANK)
    private String firstName;

    private String lastName;

    @NotBlank( message = UserConstant.PHONE_NUMBER_NOT_BLANK)
    private String phoneNumber;

    @DateTimeFormat(pattern = DateTimeConstant.DD_MM_YYYY)
    private LocalDate dateOfBirth;

    @Email(message = UserConstant.EMAIL_NOT_VALID)
    private String email;

    private User createdBy;

    private Date createdAt;

    private User modifiedBy;

    private Date modifiedAt;

    @NotBlank( message = UserConstant.USERNAME_NOT_BLANK)
    private String username;

    @NotBlank( message = UserConstant.AUTHENTICATION_CODE_NOT_BLANK)
    private String authenticationCode; // password

    private List<RoleDTO> roles;


}
