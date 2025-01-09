package com.outsourcing.dto.order;

import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
public class OrderRequestDto {

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "Store name is required")
    private String storeName;

    @Positive(message = "Menu price must be greater than 0")
    private int menuPrice;

    @NotBlank(message = "Menu name is required")
    private String menuName;

    @Pattern(regexp = "ORDER_CONFIRMED|COOKING_STARTED|DELIVERY_IN_PROGRESS|DELIVERY_COMPLETED",
            message = "Invalid order status")
    private String status;

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