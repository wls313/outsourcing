package com.outsourcing.common.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String storeName;
    private int menuPrice;  // 변경된 부분
    private String menuName;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(String userName, String storeName, int menuPrice, String menuName, OrderStatus status) {
        this.userName = userName;
        this.storeName = storeName;
        this.menuPrice = menuPrice;  // 변경된 부분
        this.menuName = menuName;
        this.status = status;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}