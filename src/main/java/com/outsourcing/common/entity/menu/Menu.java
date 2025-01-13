package com.outsourcing.common.entity.menu;

import com.outsourcing.common.entity.store.Store;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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

    private Integer menuPrice;

    private boolean menuStatus;

    public Menu(){
    }

    public static Menu create(String menuName,String menuDescription, Integer menuPrice,Store store){
        Menu menu = new Menu();

        menu.menuName = menuName;
        menu.menuDescription = menuDescription;
        menu.menuPrice = menuPrice;
        menu.store = store;

        return menu;
    }

    public void updateMenu(String menuName,String menuDescription, Integer menuPrice ){
        if(menuName != null && !menuName.isEmpty()){
            this.menuName = menuName;
        }
        if(menuDescription != null && !menuDescription.isEmpty()){
            this.menuDescription = menuDescription;
        }
        if(menuPrice != null){
            this.menuPrice = menuPrice;
        }
    }

    public void updateStatus(Boolean menuStatus){
        this.menuStatus = menuStatus;
    }

    public boolean isStatus() {
        return menuStatus;
    }
}