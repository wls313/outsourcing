package com.outsourcing.dto.review;

import com.outsourcing.common.entity.Review.Review;

import java.time.LocalDateTime;

public record ReviewStoreResponseDto (
        Double rating,
        String menuName,
        String userName,
        String contents,
        LocalDateTime createdAt
){
    public static ReviewStoreResponseDto toDto(Review review){
        return new ReviewStoreResponseDto(review.getRating(), review.getMenuName(), review.getUserName(), review.getContents(), review.getCreateAt());
    }
}
