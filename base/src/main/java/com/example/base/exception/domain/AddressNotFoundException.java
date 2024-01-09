package com.example.base.exception.domain;

/**
 * AddressNotFoundException là một ngoại lệ chạy khi không tìm thấy địa chỉ trong hệ thống.
 * Khi ngoại lệ này được ném, nó chứa một thông báo lỗi
 * @author Phuong Oanh
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}
