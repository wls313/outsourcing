package com.outsourcing.repository.order;

import com.outsourcing.common.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long userId);

    List<Order> findByStoreId(Long storeId);
}