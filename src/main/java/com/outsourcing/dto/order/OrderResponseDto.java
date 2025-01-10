package com.outsourcing.dto.order;

import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private Long id; //오더 아이디

    private String storeName;//가게 이름 or 아이디

    private String menuName;//메뉴 이름 or 아이디

    private int menuPrice;

    private OrderStatus status;




    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.storeName = order.getStore().getStoreName();
        this.menuName = order.getMenu().getMenuName();
        this.menuPrice = order.getMenu().getMenuPrice();
        this.status = order.getStatus();
    }



    public static OrderResponseDto toDto(Order order){
        return new OrderResponseDto(
                order.getId(),
                order.getStore().getStoreName(),
                order.getMenu().getMenuName(),
                order.getMenu().getMenuPrice(),
                order.getStatus()
        );
    }
}