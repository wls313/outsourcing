package com.outsourcing.dto.store;

import com.outsourcing.common.entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllStoreResponseDto {

    private final String StoreName;

    public static GetAllStoreResponseDto toDto(Store store) {

        return new GetAllStoreResponseDto(store.getStoreName());
    }
}