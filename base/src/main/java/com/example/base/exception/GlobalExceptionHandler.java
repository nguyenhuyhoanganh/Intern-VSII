package com.example.base.exception;

import com.example.base.model.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException exception, WebRequest request) {
        ResponseDTO<?> responseBody = ResponseDTO.builder().message("Server error, please try again later").code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return new ResponseEntity<Object>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
