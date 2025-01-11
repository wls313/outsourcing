package com.outsourcing.common.exception.store;

import lombok.Getter;

@Getter
public class StoreNotFoundException extends RuntimeException {

    private String errorMessage;

    public StoreNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
