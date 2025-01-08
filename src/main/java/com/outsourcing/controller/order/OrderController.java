package com.outsourcing.controller.order;

import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import com.outsourcing.dto.order.OrderRequestDto;
import com.outsourcing.service.order.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderRequestDto.toOrder();  // OrderRequestDto에서 직접 변환
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, OrderStatus.valueOf(status.toUpperCase()));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}