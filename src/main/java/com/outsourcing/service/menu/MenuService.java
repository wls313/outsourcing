package com.outsourcing.service.menu;

import com.outsourcing.common.entity.menu.Menu;
import com.outsourcing.common.entity.store.Store;
import com.outsourcing.common.exception.menu.MenuIdException;
import com.outsourcing.common.exception.review.ReviewUserIdException;
import com.outsourcing.dto.menu.MenuDto;
import com.outsourcing.dto.menu.MenuRequestDto;
import com.outsourcing.dto.menu.MenuResponseDto;
import com.outsourcing.repository.menu.MenuRepository;
import com.outsourcing.repository.store.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public MenuDto createMenu(Long storeId, MenuRequestDto requestDto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewUserIdException(storeId));

        Menu menu = Menu.create(requestDto.menuName(), requestDto.menuDescription(), requestDto.menuPrice(), store);

        menuRepository.save(menu);

        return MenuDto.from(menu);
    }

    @Transactional
    public MenuResponseDto updateMenu(Long id, MenuRequestDto requestDto) {
        Menu menu = findById(id);

        menu.updateMenu(requestDto.menuName(), requestDto.menuDescription(), requestDto.menuPrice());

        return new MenuResponseDto(menu.getMenuName(), menu.getMenuDescription(), menu.getMenuPrice());
    }

    @Transactional
    public void deleteMenu(Long id) {
        Menu menu = findById(id);
        menu.updateStatus(true);
        menuRepository.save(menu);
    }

    private Menu findById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuIdException(id));

        return menu;
    }
}
