package com.outsourcing.controller.order;

import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import com.outsourcing.dto.order.OrderRequestDto;
import com.outsourcing.dto.order.OrderResponseDto;
import com.outsourcing.service.order.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        return new OrderResponseDto(
                orderService.getOrderById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Order not found"))
        );
    }

    @PutMapping("/{id}/status")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        Order order = orderService.updateOrderStatus(id, OrderStatus.valueOf(status.toUpperCase()));
        return new OrderResponseDto(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}