package com.outsourcing.common.entity.menu;

import com.outsourcing.common.entity.store.Store;
import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String menuName;

    private String menuDescription;

    private int menuPrice;

    private boolean menuStatus;
}
