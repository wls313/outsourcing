package com.outsourcing.service.store;

import com.outsourcing.common.entity.store.Store;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.common.exception.IdNotFoundExcetion;
import com.outsourcing.common.exception.store.CreateUnauthorizedException;
import com.outsourcing.common.exception.store.StoreNotFoundException;
import com.outsourcing.common.exception.store.StoreShutDownException;
import com.outsourcing.common.exception.store.UnableCreateStoreException;
import com.outsourcing.dto.menu.MenuResponseDto;
import com.outsourcing.dto.store.*;
import com.outsourcing.repository.menu.MenuRepository;
import com.outsourcing.repository.store.StoreRepository;
import com.outsourcing.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    // 생성
    public CreateStoreResponseDto createStore(Long id, CreateStoreRequestDto requestDto) {

        User foundUser = userRepository.findById(id).orElseThrow(() -> new IdNotFoundExcetion("아이디를 확인해주세요"));

        if (!foundUser.getRole().equals("owner")) {
            throw new CreateUnauthorizedException("사장님만 가게를 생성할 수 있습니다.");
//                    ResponseStatusException(HttpStatus.UNAUTHORIZED, "사장님만 가게를 생성할 수 있습니다.");
        }

        Long countStore = storeRepository.countStoreByUser(id);

        if (countStore >= 3) {
            throw new UnableCreateStoreException("가게는 3개까지 생성할 수 있습니다.");
//                    ResponseStatusException(HttpStatus.BAD_REQUEST, "가게는 3개까지 생성할 수 있습니다.");
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

        Store foundStore = findById(id);

        List<MenuResponseDto> menuList = menuRepository.findMenuByStoreId(foundStore.getId())
                .stream()
                .map(m -> new MenuResponseDto(
                        m.getMenuName(),
                        m.getMenuDescription(),
                        m.getMenuPrice()))
                .toList();

        if (foundStore.isStoreStatus() == false) {

            throw new StoreShutDownException("폐업된 가게입니다.");
//                    ResponseStatusException(HttpStatus.NOT_FOUND, "폐업된 가게입니다.");
        }
        return GetStoreResponseDto.getStoreDto(foundStore, menuList);
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
                orElseThrow(() -> new StoreNotFoundException("매장이 없습니다."));
    }
}
