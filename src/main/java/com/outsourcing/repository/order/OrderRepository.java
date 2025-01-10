package com.outsourcing.repository.order;

import com.outsourcing.common.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Optional<Order> findByOrderId(@Param("id") Long id);
}