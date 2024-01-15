package com.example.base.constant;

import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Là 1 class chứa các hằng số liên quan đến kiểm tra dữ liệu của địa chỉ.
 * @author Phuong Oanh
 */
@Component
public class AddressConstant {

//    @Autowired
//    private static MessageService service;
//
//    public static final String LINE_NOT_BLANK = service.getMessage("validation.address.line.not_blank");
//    public static final String WARD_NOT_BLANK = service.getMessage("validation.address.ward.not_blank");
//    public static final String DISTRICT_NOT_BLANK = service.getMessage("validation.address.district.not_blank");
//    public static final String PROVINCE_NOT_BLANK = service.getMessage("validation.address.province.not_blank");
//
//    public static final String ADDRESS_NOT_FOUND = service.getMessage("validation.address.not_found");
//    public static final String USER_NOT_FOUND = service.getMessage("exception.user.not_found");

    public static final String ADDRESS_NOT_FOUND = "Địa chỉ không tồn tại";
    public static final String USER_NOT_FOUND = "Người dùng không tồn tại";
    public static final String LINE_NOT_BLANK = "Tên đường không được để trống";
    public static final String WARD_NOT_BLANK = "Tên xã/phường không được để trống";
    public static final String DISTRICT_NOT_BLANK = "Tên quận/huyện không được để trống";
    public static final String PROVINCE_NOT_BLANK = "Tên tỉnh/thành phố không được để trống";
}
