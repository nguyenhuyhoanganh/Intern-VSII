package com.example.base.constant;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Configuration
public class UserConstant {
    private static MessageSource messageSource;

    public static String AGE_NOT_VALID;

    public UserConstant(MessageSource messageSource) {
        UserConstant.messageSource = messageSource;
        AGE_NOT_VALID = messageSource.getMessage("validation.user.age.not.under.18", null, Locale.getDefault());
    }
    public static final String FIRST_NAME_NOT_BLANK = "validation.user.first_name.not_blank";
    public static final String BLANK = "";
    public static final String EMAIL_NOT_VALID = "validation.user.email.not_valid";
    public static final String PHONE_NUMBER_NOT_BLANK = "validation.user.phone_number.not_blank";
    public static final String DATE_OF_BIRTH_NOT_VALID = "validation.user.date_of_birth.not_valid";
    public static final String USER_MESSAGE_NOT_FOUND = "Người dùng không tồn tại";
    public static final String ROLE_NOT_FOUND = "Role không tồn tại";
    public static final String USER_MESSAGE_IS_NULL = "exception.user.id.not_valid";
    public static final String USER_MESSAGE_UPDATE_FAIL = "exception.user.update_fail";
    public static final String USER_NOT_FOUND_BY_USERNAME = "exception.user.not_found_by_username";

    public static final String USER_NOT_FOUND_BY_ID="exception.user.id.not_valid";

    public static final String USERNAME_NOT_BLANK = "validation.user.username.not_blank";

    public static final String AUTHENTICATION_CODE_NOT_BLANK = "exception.auth.authentication_code.not_blank";
    public static final String ID_NOT_MATCH = "Id trên url và id trong Object phải trùng nhau";

}
