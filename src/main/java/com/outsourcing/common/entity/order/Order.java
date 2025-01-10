package com.outsourcing.common.entity.order;

import com.outsourcing.common.entity.menu.Menu;
import com.outsourcing.common.entity.store.Store;
import com.outsourcing.common.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //질문
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY) //질문
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    //생성자 //메뉴 추가해야함.
    public Order(User user, Store store, Menu menu, OrderStatus status) {
        this.user = user;
        this.store = store;
        this.menu = menu;
        this.status = status;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public void updateMenu(Menu menu) {
        this.menu = menu;
    }
}