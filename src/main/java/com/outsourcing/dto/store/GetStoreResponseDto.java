package com.outsourcing.dto.store;

import com.outsourcing.common.entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetStoreResponseDto {

    private final Long id;

    private final String storeName; // 가게명

    private final String openTime; // 가게 오픈시간

    private final String closeTime; // 가게 마감시간

    private final int minimumOrderPrice; // 최소주문금액

    private final String notice; // 공지사항

    private final LocalDateTime createDate; // 오픈일

    public static GetStoreResponseDto getStoreDto(Store store) {

        return new GetStoreResponseDto(store.getId(),
                store.getStoreName(),
                store.getOpenTime(),
                store.getCloseTime(),
                store.getMinimumOrderPrice(),
                store.getNotice(),
                store.getCreateDate());
    }
}
