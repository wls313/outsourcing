package com.outsourcing.common.exception.user;

import lombok.Getter;

@Getter
public class UnavailableIdOrPasswordException extends RuntimeException{

    private String errorMessage;

    public UnavailableIdOrPasswordException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
