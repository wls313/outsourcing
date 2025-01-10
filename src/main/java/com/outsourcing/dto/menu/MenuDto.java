package com.outsourcing.dto.menu;

import com.outsourcing.common.entity.menu.Menu;

public record MenuDto(
        String menuName,
        String menuDescription,
        Integer menuPrice
) {
    public static MenuDto from(Menu menu){
        return new MenuDto(
            menu.getMenuName(),
            menu.getMenuDescription(),
            menu.getMenuPrice()
        );
    }
}