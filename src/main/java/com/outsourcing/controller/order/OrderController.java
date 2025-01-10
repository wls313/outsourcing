package com.outsourcing.controller.order;

import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.dto.order.OrderRequestDto;
import com.outsourcing.dto.order.OrderResponseDto;
import com.outsourcing.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    //손님이 주문
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto requestDto
            ){

        Order order= orderService.createOrderService(requestDto);

        OrderResponseDto orderResponseDto = new OrderResponseDto(order);

        return new ResponseEntity<>(orderResponseDto, HttpStatus.CREATED);
    }

    //손님 주문 목록 조회
    @GetMapping("/order/{userId}")
    public ResponseEntity<List<OrderResponseDto>> foundOrderByUserId(
            @PathVariable("userId")Long id,
            @RequestBody User user
    ){

        List<OrderResponseDto> orderResponseDto = orderService.foundOrderByUserIdService(user);

        return new ResponseEntity<>(orderResponseDto, HttpStatus.FOUND);

    }

    //매장 주문 목록 조회
    @GetMapping("/order/{storeId}")
    public ResponseEntity<List<OrderResponseDto>> findAllOrder(@PathVariable("storeId")Long storeId){

        List<OrderResponseDto> orderResponseDto = orderService.findAllOrderByStoreIdService(storeId);

        return new ResponseEntity<>(orderResponseDto, HttpStatus.FOUND);

    }

    //주문 삭제
    @DeleteMapping("/order/{userId}/{orderId}")
    public ResponseEntity<String> orderCancellation(
            @PathVariable Long userId,
            @PathVariable Long orderId
    ){
        orderService.orderCancellationService(userId,orderId);

        return new ResponseEntity<>("주문이 취소되었습니다.", HttpStatus.OK);
    }
    //주문 상태 변경

    @PatchMapping("/order/{userId}/{orderId}")
    public ResponseEntity<String> changeOrderStatus(
            @PathVariable Long userId,
            @PathVariable Long orderId
    ){
        orderService.changeOrderStatusService(userId,orderId);

        return new ResponseEntity<>("주문 상태가 변경되었습니다.", HttpStatus.OK);
    }
}