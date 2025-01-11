package com.outsourcing.common.exception.user;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private String errorMessage;

    public NotFoundException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
