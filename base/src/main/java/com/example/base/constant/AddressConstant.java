package com.example.base.constant;

import com.example.base.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Phuong Oanh
 */
@Component
public class AddressConstant {
    @Autowired
    private static MessageService service;

    public static final String LINE_NOT_BLANK = service.getMessage("validation.address.line.not_blank");
    public static final String WARD_NOT_BLANK = service.getMessage("validation.address.ward.not_blank");
    public static final String DISTRICT_NOT_BLANK = service.getMessage("validation.address.district.not_blank");
    public static final String PROVINCE_NOT_BLANK = service.getMessage("validation.address.province.not_blank");

}
