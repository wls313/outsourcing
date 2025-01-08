package com.outsourcing.controller.store;

import com.outsourcing.dto.store.CreateStoreRequestDto;
import com.outsourcing.dto.store.CreateStoreResponseDto;
import com.outsourcing.dto.store.GetAllStoreResponseDto;
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

    @PostMapping
    public ResponseEntity<CreateStoreResponseDto> createStoreAPI(@RequestBody CreateStoreRequestDto requestDto) {

        CreateStoreResponseDto createdStore = storeService.createStore(requestDto);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetAllStoreResponseDto>> getAllStoreAPI() {
        List<GetAllStoreResponseDto> allStore = storeService.getAllStore();
        return new ResponseEntity<>(allStore, HttpStatus.OK);
    }
}
