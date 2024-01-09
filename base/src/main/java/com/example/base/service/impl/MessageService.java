package com.example.base.service.impl;

import com.example.base.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;



/**
 * Triển khi của interface IMessageService truy xuất message từ MessageSource.
 */
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    /***
     * Tiêm Bean MessageSource vào MessageService
     */
    private final MessageSource messageSource;

    /**
     * Method lấy messages từ code.
     *
     * @param code mã định danh cho message.
     * @return Message dựa trên code và giá trị locale trên hệ thống.
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}