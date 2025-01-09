package com.outsourcing.common.exception.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends RuntimeException{

    @ExceptionHandler(ReviewUserNameException.class)
    public ResponseEntity<String> handleMemberIdException(ReviewUserNameException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ReviewUserIdException.class)
    public ResponseEntity<String> handleMemberIdException(ReviewUserIdException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ReviewOrderIdException.class)
    public ResponseEntity<String> handleMemberIdException(ReviewOrderIdException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
