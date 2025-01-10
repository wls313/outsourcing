package com.outsourcing.controller.menu;

import com.outsourcing.dto.menu.MenuDto;
import com.outsourcing.dto.menu.MenuRequestDto;
import com.outsourcing.dto.menu.MenuResponseDto;
import com.outsourcing.dto.store.UpdateStoreResponseDto;
import com.outsourcing.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/menu")
@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/{storeId}")
    public ResponseEntity<MenuDto> create(@PathVariable Long storeId, @RequestBody MenuRequestDto requestDto){
        MenuDto response = menuService.createMenu(storeId,requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MenuResponseDto> updateMenu(@PathVariable Long id, @RequestBody MenuRequestDto requestDto){
        MenuResponseDto response = menuService.updateMenu(id,requestDto);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UpdateStoreResponseDto> deleteStoreAPI(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
