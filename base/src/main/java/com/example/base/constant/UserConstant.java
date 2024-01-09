package com.example.base.constant;

import com.example.base.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConstant {
//    @Autowired
//    private static MessageService service;
//    public static final String FIRST_NAME_NOT_BLANK = service.getMessage("validation.user.first_name.not_blank");
//    public static final String EMAIL_NOT_VALID = service.getMessage("validation.user.email.not_valid");
//    public static final String PHONE_NUMBER_NOT_BLANK = service.getMessage("validation.user.phone_number.not_blank");
//    public static final String DATE_OF_BIRTH_NOT_VALID = service.getMessage("validation.user.date_of_birth.not_valid");
//    public static final String USER_MESSAGE_NOT_FOUND = service.getMessage("exception.user.not_found");
//    public static final String USER_MESSAGE_IS_NULL = service.getMessage("exception.user.id.not_valid");
//    public static final String USER_MESSAGE_UPDATE_FAIL = service.getMessage("exception.user.update_fail");
//    public static final String USER_NOT_FOUND_BY_USERNAME = service.getMessage("exception.user.not_found_by_username");
//    public static final String USERNAME_NOT_BLANK = service.getMessage("validation.user.username.not_blank");
//    public static final String AUTHENTICATION_CODE_NOT_BLANK = service.getMessage("exception.auth.authentication_code.not_blank");

    public static final String FIRST_NAME_NOT_BLANK = "First name không được trống";
    public static final String EMAIL_NOT_VALID = "Email không hợp lệ";
    public static final String PHONE_NUMBER_NOT_BLANK = "Số điện thoại không được trống";
    public static final String DATE_OF_BIRTH_NOT_VALID = "Ngày sinh không hợp lệ hoặc có kiêu 'DD-MM-YYYY'";
    public static final String USER_MESSAGE_NOT_FOUND = "Người dùng không tồn tại";
    public static final String USER_MESSAGE_IS_NULL = "Id không hợp lệ. Id là null";
    public static final String USER_MESSAGE_UPDATE_FAIL = "Update User thất bại !";
    public static final String USER_NOT_FOUND_BY_USERNAME = "Không tìm thấy người dùng có username: ";
    public static final String USERNAME_NOT_BLANK = "Username không được trống";
    public static final String AUTHENTICATION_CODE_NOT_BLANK = "Authentication code không được trống";
}
