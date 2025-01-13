package com.outsourcing.common.exception.store;

import lombok.Getter;

@Getter
public class StoreShutDownException extends RuntimeException {

    private String errorMessage;

    public StoreShutDownException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
