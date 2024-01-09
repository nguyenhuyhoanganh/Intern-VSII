package com.example.base.service;

/**
 * Interface dành cho MessageService dùng cho việc truy xuất messages dựa trên code.
 */
public interface IMessageService {

    /**
     * Method lấy messages từ code.
     *
     * @param code mã định danh cho message.
     * @return Message dựa trên code.
     */
    String getMessage(String code);
}