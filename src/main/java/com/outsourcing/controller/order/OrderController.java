package com.outsourcing.controller.order;

import com.outsourcing.entity.order.Order;
import com.outsourcing.entity.order.OrderStatus;
import com.outsourcing.dto.order.OrderRequestDto;
import com.outsourcing.service.order.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")  // "orders" -> "order"
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = new Order(
                orderRequestDto.getMemberName(),
                orderRequestDto.getStoreName(),
                orderRequestDto.getAmount(),
                orderRequestDto.getMenuName(),
                OrderStatus.valueOf(orderRequestDto.getStatus().toUpperCase())
        );
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