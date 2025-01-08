package com.outsourcing.common.exception;

import lombok.Getter;

@Getter
public class UnavailableIdOrPasswordException extends RuntimeException{

    private String errorMessage;

    public UnavailableIdOrPasswordException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
