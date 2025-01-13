package com.outsourcing.common.config;

import com.outsourcing.common.entity.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Aspect
public class AspectPractice {
    @Pointcut("execution(* com.outsourcing.service.order.OrderService.createOrderService(..))")
    public void createOrderPointcut(){}

    //로그에는 요청 시각, 가게 id, 주문 id가 필수로 포함되어야합니다.
    @AfterReturning(pointcut = "createOrderPointcut()", returning = "result")
    public void createOrderLog(Object result){

            Order order = (Order) result;

            LocalDateTime orderTime = LocalDateTime.now();

            log.info("주문 요청시각 : {}", createdDate(orderTime));
            log.info("가게 ID : {}", order.getStore().getId());
            log.info("주문 ID : {}", order.getId());

    }

    public static String createdDate(LocalDateTime orderTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderTime.format(formatter);
    }
}
