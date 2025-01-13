package com.outsourcing.common.exception.user;

import com.outsourcing.common.exception.menu.MenuIdException;
import com.outsourcing.common.exception.review.ReviewOrderIdException;
import com.outsourcing.common.exception.review.ReviewUserIdException;
import com.outsourcing.common.exception.review.ReviewUserNameException;
import com.outsourcing.common.exception.store.CreateUnauthorizedException;
import com.outsourcing.common.exception.store.StoreNotFoundException;
import com.outsourcing.common.exception.store.StoreShutDownException;
import com.outsourcing.common.exception.store.UnableCreateStoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleIdNotFoundException(NotFoundException idNotFoundExcetion){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.NOT_FOUND, idNotFoundExcetion.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnavailableIdOrPasswordException.class)
    public ResponseEntity<ApiResponse<?>> handleUnavailableIdOrPasswordException(UnavailableIdOrPasswordException unavailableIdOrPasswordException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST, unavailableIdOrPasswordException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArgumentMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handlePasswordMismatchException(ArgumentMismatchException passwordMismatchException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.UNAUTHORIZED, passwordMismatchException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String errorMessage = "";

        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()){

            errorMessage = errorMessage + fieldError.getDefaultMessage();
        }
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(MenuIdException.class)
    public ResponseEntity<String> handleMenuIdException(MenuIdException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

//    @ExceptionHandler(StoreNotFoundException.class)
//    public ResponseEntity<String> handleStoreNotFoundException(StoreNotFoundException ex){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleStoreNotFoundException(StoreNotFoundException storeNotFoundException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.NOT_FOUND, storeNotFoundException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StoreShutDownException.class)
    public ResponseEntity<ApiResponse<?>> handleStoreShutDownException(StoreShutDownException storeShutDownException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.NOT_FOUND, storeShutDownException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreateUnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleCreateUnauthorizedException(CreateUnauthorizedException createUnauthorizedException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.UNAUTHORIZED, createUnauthorizedException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnableCreateStoreException.class)
    public ResponseEntity<ApiResponse<?>> handleUnableCreateStoreException(UnableCreateStoreException unableCreateStoreException){
        ApiResponse<Object> errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST, unableCreateStoreException.getErrorMessage());
        return new ResponseEntity<ApiResponse<?>>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
