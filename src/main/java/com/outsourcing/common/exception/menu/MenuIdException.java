package com.outsourcing.common.exception.menu;

public class MenuIdException extends RuntimeException{
    public MenuIdException(Long id){
        super(id + "를 가진 메뉴가 존재하지않습니다.");
    }
}