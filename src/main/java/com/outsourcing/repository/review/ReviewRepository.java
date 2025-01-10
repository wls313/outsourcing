package com.outsourcing.repository.review;

import com.outsourcing.common.entity.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByStoreId(Long storeId);

    List<Review> findByUserId(Long UserId);
}
