package com.outsourcing.common.exception;

import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException{

    private String errorMessage;

    public PasswordMismatchException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
