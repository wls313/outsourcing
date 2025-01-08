package com.outsourcing.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundExcetion.class)
    public ResponseEntity<ApiResponse<?>> handleIdNotFoundException(IdNotFoundExcetion idNotFoundExcetion){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.NOT_FOUND, idNotFoundExcetion.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnavailableIdOrPasswordException.class)
    public ResponseEntity<ApiResponse<?>> handleUnavailableIdOrPasswordException(UnavailableIdOrPasswordException unavailableIdOrPasswordException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST, unavailableIdOrPasswordException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handlePasswordMismatchException(PasswordMismatchException passwordMismatchException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.UNAUTHORIZED, passwordMismatchException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String errorMessage = "";

        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()){

            errorMessage = errorMessage + fieldError.getDefaultMessage();
        }
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
