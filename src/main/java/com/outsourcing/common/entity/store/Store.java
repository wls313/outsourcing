package com.outsourcing.common.entity.store;

import com.outsourcing.common.entity.BaseEntity;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.dto.store.CreateStoreRequestDto;
import com.outsourcing.dto.store.UpdateStoreRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "store")
@NoArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storeName; // 가게명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String openTime; // 가게 오픈시간
    private String closeTime; // 가게 마감시간

    private int minimumOrderPrice; // 최소주문금액

    private boolean storeStatus; // 폐업여부(false:폐업, true:영업)

    private String notice; // 공지사항

    public Store(CreateStoreRequestDto requestDto, User user) {
        this.storeName = requestDto.getStoreName();
        this.openTime = requestDto.getOpenTime();
        this.closeTime = requestDto.getCloseTime();
        this.minimumOrderPrice = requestDto.getMinimumOrderPrice();
        this.notice = requestDto.getNotice();
        this.storeStatus = true;
        this.user = user;
    }

    public void updateStatus(boolean storeStatus) {
        this.storeStatus = storeStatus;
    }

    public void updateStore(UpdateStoreRequestDto requestDto) {

        if (requestDto.getStoreName() != null) {
            this.storeName = requestDto.getStoreName();
        }
        if (requestDto.getOpenTime() != null) {
            this.openTime = requestDto.getOpenTime();
        }
        if (requestDto.getCloseTime() != null) {
            this.closeTime = requestDto.getCloseTime();
        }
        if (requestDto.getMinimumOrderPrice() != null) {
            this.minimumOrderPrice = requestDto.getMinimumOrderPrice();
        }
        if (requestDto.getNotice() != null) {
            this.notice = requestDto.getNotice();
        }
    }
}
