package com.outsourcing.dto.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderRequestDto {

    private final String userName;  // 회원명
    private final String storeName;   // 가거명
    private final int amount;         // 금액
    private final String menuName;    // 메뉴명
    private final String status;      // 상태
}