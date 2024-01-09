package com.example.base.exception.domain;

/**
 * Khi không tìm thấy User thì sẽ throw ra UserNotFoundException
 * Định nghĩa để tạo 1 UserNotFoundException
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
