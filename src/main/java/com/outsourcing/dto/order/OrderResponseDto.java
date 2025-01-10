package com.outsourcing.dto.order;

import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private Long id;
    private String userName;
    private String storeName;
    private String menuName;
    private int menuPrice;
    private OrderStatus status;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.userName = order.getUserName();
        this.storeName = order.getStoreName();
        this.menuName = order.getMenuName();
        this.menuPrice = order.getMenuPrice();
        this.status = order.getStatus();
    }
}