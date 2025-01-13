package com.outsourcing.common.exception.store;

import lombok.Getter;

@Getter
public class UnableCreateStoreException extends RuntimeException {

    private String errorMessage;

    public UnableCreateStoreException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
