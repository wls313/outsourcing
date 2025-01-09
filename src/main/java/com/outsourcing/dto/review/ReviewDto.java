package com.outsourcing.dto.review;

import com.outsourcing.common.entity.Review.Review;

import java.time.LocalDateTime;

public record ReviewDto(
        Double rating,
        String contents,
        LocalDateTime createdAt
) {
    public static ReviewDto from(Review review){
        return new ReviewDto(
                review.getRating(),
                review.getContents(),
                review.getCreateAt()
        );
    }
}
