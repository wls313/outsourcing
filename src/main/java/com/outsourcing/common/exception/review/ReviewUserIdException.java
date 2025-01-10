package com.outsourcing.common.exception.review;

public class ReviewUserIdException extends RuntimeException{
    public ReviewUserIdException(Long userId){
        super(userId + "을 가진 사용자가 존재하지않습니다.");
    }
}
