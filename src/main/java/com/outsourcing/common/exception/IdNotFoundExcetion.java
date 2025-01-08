package com.outsourcing.common.exception;

import lombok.Getter;

@Getter
public class IdNotFoundExcetion extends RuntimeException{

    private String errorMessage;

    public IdNotFoundExcetion(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
