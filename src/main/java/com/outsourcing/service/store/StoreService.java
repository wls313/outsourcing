package com.outsourcing.service.store;

import com.outsourcing.common.entity.store.Store;
import com.outsourcing.dto.store.CreateStoreRequestDto;
import com.outsourcing.dto.store.CreateStoreResponseDto;
import com.outsourcing.dto.store.GetAllStoreResponseDto;
import com.outsourcing.dto.store.GetStoreResponseDto;
import com.outsourcing.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Store findStore = storeRepository.findByIdOrElseThrow(id);
        return GetStoreResponseDto.getStoreDto(findStore);
    }
}
