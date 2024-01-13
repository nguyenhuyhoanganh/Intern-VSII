package com.example.base.exception.domain;

/**
 * @author HungDV
 * Exception được throw ra khi id trên url và id của dto không giống nhau
 */
public class IdNotMatchException extends RuntimeException{
    //constructor
    public IdNotMatchException(String message) {
        super(message);
    }
}
