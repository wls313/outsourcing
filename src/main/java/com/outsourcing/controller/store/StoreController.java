package com.outsourcing.controller.store;

import com.outsourcing.dto.store.*;
import com.outsourcing.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 가게 생성
    @PostMapping("/{id}")
    public ResponseEntity<CreateStoreResponseDto> createStoreAPI(@PathVariable Long id, @RequestBody CreateStoreRequestDto requestDto) {
        CreateStoreResponseDto createdStore = storeService.createStore(id, requestDto);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    // 가게 조회, 다건
    @GetMapping
    public ResponseEntity<List<GetAllStoreResponseDto>> getAllStoreAPI() {
        List<GetAllStoreResponseDto> allStore = storeService.getAllStore();
        return new ResponseEntity<>(allStore, HttpStatus.OK);
    }

    // 가게 조회, 단건
    @GetMapping("/{id}")
    public ResponseEntity<GetStoreResponseDto> getStoreByIdAPI(@PathVariable Long id) {
        GetStoreResponseDto findStore = storeService.getStoreById(id);
        return new ResponseEntity<>(findStore, HttpStatus.OK);
    }

    // 가게 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateStoreResponseDto> updateStoreAPI(
            @PathVariable Long id,
            @RequestBody UpdateStoreRequestDto requestDto) {
        UpdateStoreResponseDto updatedStore = storeService.updateStore(id, requestDto);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    // 가게 폐업
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UpdateStoreResponseDto> deleteStoreAPI(@PathVariable Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
