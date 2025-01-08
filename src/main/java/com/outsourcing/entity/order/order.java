package com.outsourcing.entity.order;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;  // 고객명 -> 회원명
    private String storeName;   // 가게명
    private int amount;         // 금액 (수량 -> 금액)
    private String menuName;    // 메뉴명 (제품명)

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 상태

    private LocalDateTime createdAt;

    protected Order() {
        this.createdAt = LocalDateTime.now();
    }

    public Order(String memberName, String storeName, int amount, String menuName, OrderStatus status) {
        this.memberName = memberName;
        this.storeName = storeName;
        this.amount = amount;
        this.menuName = menuName;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getAmount() {
        return amount;
    }

    public String getMenuName() {
        return menuName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}