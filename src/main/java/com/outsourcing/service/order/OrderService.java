package com.outsourcing.service.order;

import com.outsourcing.common.entity.menu.Menu;
import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import com.outsourcing.common.entity.store.Store;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.dto.order.OrderRequestDto;
import com.outsourcing.dto.order.OrderResponseDto;
import com.outsourcing.repository.menu.MenuRepository;
import com.outsourcing.repository.order.OrderRepository;
import com.outsourcing.repository.store.StoreRepository;
import com.outsourcing.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    //주문 생성
    public Order createOrderService(OrderRequestDto requestDto){

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다"));

        if(!user.getRole().equals("user")){
            throw new RuntimeException("손님만 주문이 가능합니다.");
        }

        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(()->new RuntimeException("해당 매장을 찾을수없습니다."));


        LocalTime now = LocalTime.now();

        LocalTime openTime = LocalTime.parse(store.getOpenTime());
        LocalTime closeTime = LocalTime.parse(store.getCloseTime());

        if(now.isBefore(openTime) || now.isAfter(closeTime)){
            throw new RuntimeException("지금은 영업시간이 아닙니다");
        }

        Menu orderMenu = menuRepository.findById(requestDto.getMenuId())
                .orElseThrow(()->new RuntimeException("해당 메뉴를 찾을수없습니다."));

        int totalPrice = orderMenu.getMenuPrice();

        if(totalPrice < store.getMinimumOrderPrice()){
            throw new RuntimeException("최소주문금액을 확인하세요");
        }

        Order order = new Order(user, store, orderMenu, OrderStatus.ORDER_CONFIRMED);

        return orderRepository.save(order);

    }
    //주문 유저별 조회
    public List<OrderResponseDto> foundOrderByUserIdService(Long userId){

        List<Order> orderList = orderRepository.findByUserId(userId);

        List<OrderResponseDto> result = new ArrayList<>();

        for(Order order : orderList){
            OrderResponseDto orderResponseDto = OrderResponseDto.toDto(order);
            result.add(orderResponseDto);
        }

        return result;
    }

    //주문 매장별 조회
    public List<OrderResponseDto> findAllOrderByStoreIdService(Long storeId){
        List<Order> findAll = orderRepository.findByStoreId(storeId);

        List<OrderResponseDto> result = new ArrayList<>();

        for(Order order : findAll){
            OrderResponseDto orderResponseDto = OrderResponseDto.toDto(order);
            result.add(orderResponseDto);
        }
        return result;
    }

    public void orderCancellationService(Long userId, Long orderId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("유저를 찾을 수 없습니다"));
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("주문을 찾을 수 없습니다"));

        if(!user.getRole().equals("user")){
            throw new RuntimeException("주문을 취소할 권한이 없습니다.");
        }

        if (order.getStatus().equals(OrderStatus.COOKING_STARTED)){
            throw new RuntimeException("이미 주문이 수락되었습니다.");
        }

        order.updateStatus(OrderStatus.ORDER_CANCELLATION);

        orderRepository.save(order);
    }

    public void changeOrderStatusService(Long userId, Long orderId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("유저를 찾을 수 없습니다"));
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("주문을 찾을 수 없습니다"));

        if(!user.getRole().equals("owner")){
            throw new RuntimeException("주문상태를 변경할 권한이 없습니다.");
        }

        order.updateStatus(OrderStatus.COOKING_STARTED);

        orderRepository.save(order);
    }



}