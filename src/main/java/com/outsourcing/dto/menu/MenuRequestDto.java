package com.outsourcing.dto.menu;

public record MenuRequestDto (
        String menuName,
        String menuDescription,
        Integer menuPrice
){
}
