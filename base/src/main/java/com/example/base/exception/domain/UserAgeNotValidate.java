package com.example.base.exception.domain;

/**
 * @author thang
 */
public class UserAgeNotValidate extends  RuntimeException{
    public UserAgeNotValidate(String message) {
        super(message);
    }
}
