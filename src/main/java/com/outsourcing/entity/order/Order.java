package com.outsourcing.common.entitiy.order;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String productName;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private com.outsourcing.common.entity.OrderStatus status;

    private LocalDateTime createdAt;

    protected Order() {
        this.createdAt = LocalDateTime.now();
    }

    public Order(String customerName, String productName, int quantity, com.outsourcing.common.entity.OrderStatus status) {
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStatus(com.outsourcing.common.entity.OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public com.outsourcing.common.entity.OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}