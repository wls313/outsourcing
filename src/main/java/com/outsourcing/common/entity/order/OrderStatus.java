package com.outsourcing.common.entity.order;

public enum OrderStatus {
    ORDER_CONFIRMED,    // 주문 확인중
    COOKING_STARTED,    // 조리 시작
    DELIVERY_IN_PROGRESS,  // 배달중
    DELIVERY_COMPLETED,  // 배달 완료
    ORDER_CANCELLATION //주문 취소
}