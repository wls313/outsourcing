package com.outsourcing.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateStoreRequestDto {

    private String storeName; // 가게명

    private String openTime; // 가게 오픈시간

    private String closeTime; // 가게 마감시간

    private double minimumOrderPrice; // 최소주문금액

    private String notice; // 공지사항
}
