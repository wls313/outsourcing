package com.outsourcing.common.entity.store;

import com.outsourcing.common.entity.BaseEntity;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.dto.store.CreateStoreRequestDto;
import com.outsourcing.dto.store.UpdateStoreRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "store")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    // BaseEntity를 상속받아 해당 기능 주석처리
//    @CreatedDate
//    @Column(updatable = false)
//    private LocalDateTime createDate; // 오픈일

    public Store(CreateStoreRequestDto requestDto) {
        this.storeName = requestDto.getStoreName();
        this.openTime = requestDto.getOpenTime();
        this.closeTime = requestDto.getCloseTime();
        this.minimumOrderPrice = requestDto.getMinimumOrderPrice();
        this.notice = requestDto.getNotice();
        this.storeStatus = true;
    }

    public void updateStatus(boolean storeStatus) {
        this.storeStatus = storeStatus;
    }

    public void updateStore(UpdateStoreRequestDto requestDto) {
//        this.storeName = requestDto.getStoreName() == null ? this.storeName : requestDto.getStoreName();
//        this.openTime = requestDto.getOpenTime() == null ? this.openTime : requestDto.getOpenTime();
//        this.closeTime = requestDto.getCloseTime() == null ? this.closeTime : requestDto.getCloseTime();
//        this.minimumOrderPrice = requestDto.getMinimumOrderPrice() == null ? this.minimumOrderPrice : requestDto.getMinimumOrderPrice();
//        this.notice = requestDto.getNotice() == null ? this.notice : requestDto.getNotice();

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
