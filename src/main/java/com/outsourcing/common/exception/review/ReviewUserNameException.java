package com.outsourcing.common.exception.review;

public class ReviewUserNameException extends RuntimeException{
    public ReviewUserNameException(Long userName){
        super(userName + "을 가진 사용자가 존재하지않습니다.");
    }
}
