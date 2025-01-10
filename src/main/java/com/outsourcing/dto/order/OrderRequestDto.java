package com.outsourcing.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
public class OrderRequestDto {

    //유저 아이디
    //스토어 아이디
    //메뉴 아이디

    private Long menuId;

    private Long storeId;

    private Long userId;



}