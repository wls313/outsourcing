package com.outsourcing.dto.store;

import com.outsourcing.common.entity.store.Store;
import com.outsourcing.dto.menu.MenuResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetStoreResponseDto {
    private final Long id;

    private final String storeName;

    private final String openTime;

    private final String closeTime;

    private final int minimumOrderPrice;

    private final String notice;

    private final LocalDateTime createDate;

    private final List<MenuResponseDto> menuList;

    public static GetStoreResponseDto getStoreDto(Store store, List<MenuResponseDto> menuResponseDtoList) {

        return new GetStoreResponseDto(
                store.getId(),
                store.getStoreName(),
                store.getOpenTime(),
                store.getCloseTime(),
                store.getMinimumOrderPrice(),
                store.getNotice(),
                store.getCreateAt(),
                menuResponseDtoList
        );
    }

}
