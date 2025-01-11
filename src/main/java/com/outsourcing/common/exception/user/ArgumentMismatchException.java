package com.outsourcing.common.exception.user;

import lombok.Getter;

@Getter
public class ArgumentMismatchException extends RuntimeException{

    private String errorMessage;

    public ArgumentMismatchException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
