package com.outsourcing.service.store;

import com.outsourcing.common.entity.store.Store;
import com.outsourcing.dto.store.*;
import com.outsourcing.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    // 생성
    public CreateStoreResponseDto createStore(CreateStoreRequestDto requestDto) {
        Store store = new Store(requestDto);
        Store savedStore = storeRepository.save(store);
        return CreateStoreResponseDto.createDto(savedStore);
    }

    // 조회, 다건
    public List<GetAllStoreResponseDto> getAllStore() {
        return storeRepository.findAll()
                .stream()
                .map(GetAllStoreResponseDto::getAllStoreDto)
                .toList();
    }

    // 조회, 단건
    public GetStoreResponseDto getStoreById(Long id) {
        Store findStoreById = findById(id);
        return GetStoreResponseDto.getStoreDto(findStoreById);
    }

    // 수정
    @Transactional
    public UpdateStoreResponseDto updateStore(Long id, UpdateStoreRequestDto requestDto) {
        Store findStore = findById(id);
        findStore.updateStore(requestDto);
        Store updatedStore = storeRepository.save(findStore);
        return UpdateStoreResponseDto.updateStoreDto(updatedStore);
    }

    // 삭제, 폐업

    private Store findById(Long id) {
        return storeRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 없습니다. = " + id));
    }
}
