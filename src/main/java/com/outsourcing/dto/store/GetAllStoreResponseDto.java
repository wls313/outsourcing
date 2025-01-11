package com.outsourcing.dto.store;

import com.outsourcing.common.entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllStoreResponseDto {

    private final Long id;

    private final String StoreName;

    private final boolean storeStatus;

    public static GetAllStoreResponseDto getAllStoreDto(Store store) {

        return new GetAllStoreResponseDto(store.getId(), store.getStoreName(), store.isStoreStatus());
    }
}