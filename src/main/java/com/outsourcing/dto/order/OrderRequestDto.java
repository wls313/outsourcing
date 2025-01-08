package com.outsourcing.dto.order;

import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderRequestDto {

    private final String userName;  // 회원명
    private final String storeName;   // 가게명
    private final int menuPrice;         // 금액
    private final String menuName;    // 메뉴명
    private final String status;      // 상태

    public Order toOrder() {
        return new Order(
                userName,
                storeName,
                menuPrice,
                menuName,
                OrderStatus.valueOf(status.toUpperCase())
        );
    }
}