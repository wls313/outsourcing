package com.outsourcing.dto.store;

import com.outsourcing.common.entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateStoreResponseDto {
    private final Long id;

    private final String storeName;

    private final String openTime;

    private final String closeTime;

    private final int minimumOrderPrice;

    private final String notice;

    private final LocalDateTime createDate;

    public static UpdateStoreResponseDto updateStoreDto(Store store) {

        return new UpdateStoreResponseDto(store.getId(),
                store.getStoreName(),
                store.getOpenTime(),
                store.getCloseTime(),
                store.getMinimumOrderPrice(),
                store.getNotice(),
                store.getCreateDate());
    }
}
