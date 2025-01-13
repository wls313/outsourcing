package com.outsourcing.common.exception.store;

import lombok.Getter;

@Getter
public class CreateUnauthorizedException extends RuntimeException {

    private String errorMessage;

    public CreateUnauthorizedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
