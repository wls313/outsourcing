package com.outsourcing.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateStoreRequestDto {

    private final String storeName; // 가게명

    private final String openTime; // 가게 오픈시간

    private final String closeTime; // 가게 마감시간

    private final Integer minimumOrderPrice; // 최소주문금액, 입력 받지 않을 경우도 있어서 Integer 사용

    private final String notice; // 공지사항
}
