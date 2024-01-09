package com.example.base.exception;

import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class cấu hình để bắt các Exception
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Bắt UserNotFoundException và trả về mã code và message lỗi
     *
     * @param userNotFoundException
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException userNotFoundException) {
        ResponseDTO<?> responseBody = ResponseDTO.builder().message(
                        userNotFoundException.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<Object>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception xử lý validate false
     * Bắt MethodArgumentNotValidException và trả về mã code và message lỗi
     *
     * @param ex
     * @return ResponseEntity<Object>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Ex bắt các loại ex runtime trả về code và message lỗi
     *
     * @param exception RuntimeException
     * @param request   WebRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {
        ResponseDTO<?> responseBody = ResponseDTO.builder().message(exception.getMessage()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return new ResponseEntity<Object>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Ex bắt các loại ex trả về code và message lỗi
     *
     * @param exception RuntimeException
     * @param request   WebRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(RuntimeException exception, WebRequest request) {
        ResponseDTO<?> responseBody = ResponseDTO.builder().message(exception.getMessage()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return new ResponseEntity<Object>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
