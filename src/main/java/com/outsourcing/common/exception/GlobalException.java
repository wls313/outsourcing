package com.outsourcing.common.exception;

import com.outsourcing.common.exception.review.ReviewOrderIdException;
import com.outsourcing.common.exception.review.ReviewUserIdException;
import com.outsourcing.common.exception.review.ReviewUserNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends RuntimeException{
    @ExceptionHandler(ReviewOrderIdException.class)
    public ResponseEntity<String> handleReviewOrderIdException(ReviewOrderIdException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ReviewUserIdException.class)
    public ResponseEntity<String> handleReviewUserIdException(ReviewUserIdException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ReviewUserNameException.class)
    public ResponseEntity<String> handleReviewUserNameException(ReviewUserNameException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
