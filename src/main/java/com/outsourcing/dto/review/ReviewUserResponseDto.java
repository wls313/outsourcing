package com.outsourcing.dto.review;

import com.outsourcing.common.entity.Review.Review;

import java.time.LocalDateTime;

public record ReviewUserResponseDto(
        Double rating,
        String storeName,
        String menuName,
        String userName,
        String contents,
        LocalDateTime createdAt
){
    public static ReviewUserResponseDto toDto(Review review){
        return new ReviewUserResponseDto(review.getRating(), review.getStoreName(), review.getMenuName(), review.getUserName(), review.getContents(), review.getCreateAt());
    }
}
