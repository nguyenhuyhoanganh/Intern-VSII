package com.example.base.constant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * Là 1 class chứa các hằng số liên quan đến kiểm tra dữ liệu của địa chỉ.
 * @author Phuong Oanh
 */
@RequiredArgsConstructor
@Component
public class AddressConstant {

    public static final String LINE_NOT_BLANK = "validation.address.line.not_blank";

//    public static final String WARD_NOT_BLANK = service.getMessage("validation.address.ward.not_blank");
//    public static final String DISTRICT_NOT_BLANK = service.getMessage("validation.address.district.not_blank");
//    public static final String PROVINCE_NOT_BLANK = service.getMessage("validation.address.province.not_blank");
//
//    public static final String ADDRESS_NOT_FOUND = service.getMessage("validation.address.not_found");
//    public static final String USER_NOT_FOUND = service.getMessage("exception.user.not_found");

    public static final String ADDRESS_NOT_FOUND = "Địa chỉ không tồn tại";
    public static final String ADDRESS_IS_NULL = "Id phải null";
    public static final String ADDRESS_NOT_MATCH = "Id không khớp với id được truyền";
    public static final String USER_NOT_FOUND = "validation.address.not_found";
    public static final String WARD_NOT_BLANK = "validation.address.ward.not_blank";
    public static final String DISTRICT_NOT_BLANK = "validation.address.district.not_blank";
    public static final String PROVINCE_NOT_BLANK = "validation.address.province.not_blank";
}
