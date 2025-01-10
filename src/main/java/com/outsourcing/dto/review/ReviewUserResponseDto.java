package com.outsourcing.dto.review;

import com.outsourcing.common.entity.Review.Review;

import java.time.LocalDateTime;

public record ReviewUserResponseDto(
        Double rating,
        Long storeId,
        Long menuId,
        Long userId,
        String contents,
        LocalDateTime createdAt
){
    public static ReviewUserResponseDto toDto(Review review){
        return new ReviewUserResponseDto(
                review.getRating(),
                review.getStore().getId(),
                review.getOrder().getMenu().getId(),
                review.getUser().getId(),
                review.getContents(),
                review.getCreateAt()
        );
    }
}
