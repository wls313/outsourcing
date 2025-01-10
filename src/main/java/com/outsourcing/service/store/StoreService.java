package com.outsourcing.service.store;

import com.outsourcing.common.entity.store.Store;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.dto.store.*;
import com.outsourcing.repository.store.StoreRepository;
import com.outsourcing.repository.user.UserRepository;
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
    private final UserRepository userRepository;

    // 생성
    public CreateStoreResponseDto createStore(Long userId, CreateStoreRequestDto requestDto) {

        User foundUser = userRepository.findByIdOrElseThrow(userId);
        if (!foundUser.getRole().equals("owner")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사장님만 가게를 생성할 수 있습니다.");
        }

        Long countStore = storeRepository.countStoreByUser(userId);

        if (countStore >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가게는 3개까지 생성할 수 있습니다.");
        }

        Store store = new Store(requestDto, foundUser);
        Store savedStore = storeRepository.save(store);
//        List<Store> stores = new ArrayList<>();
//        stores.add(savedStore);
//        if (stores.size() > 3) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가게는 3개까지 생성할 수 있습니다.");
//        }
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
    @Transactional
    public void deleteStore(Long id) {
        Store findStore = findById(id);
        findStore.updateStatus(false);
        storeRepository.save(findStore);
    }

    private Store findById(Long id) {
        return storeRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "매장이 없습니다. = " + id));
    }
}
