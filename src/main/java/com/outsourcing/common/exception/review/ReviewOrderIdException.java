package com.outsourcing.common.exception.review;

public class ReviewOrderIdException extends RuntimeException{
    public ReviewOrderIdException(Long orderId){
        super(orderId + "를 가진 주문이 존재하지않습니다.");
    }
}
