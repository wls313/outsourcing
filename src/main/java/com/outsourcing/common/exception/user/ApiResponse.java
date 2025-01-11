package com.outsourcing.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse <T> {

    private HttpStatus status;

    private String message;

    private ApiResponse(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public static<T> ApiResponse <T> error(HttpStatus status, String message){
        return new ApiResponse<>(status, message);
    }
}
