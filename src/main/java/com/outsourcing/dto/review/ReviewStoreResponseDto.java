package com.outsourcing.dto.review;

import com.outsourcing.common.entity.Review.Review;

import java.time.LocalDateTime;

public record ReviewStoreResponseDto (
        Double rating,
        Long menuId,
        Long userId,
        String contents,
        LocalDateTime createdAt
){
    public static ReviewStoreResponseDto toDto(Review review){
        return new ReviewStoreResponseDto(
                review.getRating(),
                review.getOrder().getMenu().getId(),
                review.getUser().getId(),
                review.getContents(),
                review.getCreateAt());
    }
}
