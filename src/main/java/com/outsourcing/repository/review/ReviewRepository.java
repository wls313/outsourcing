package com.outsourcing.repository.review;

import com.outsourcing.common.entity.Order;
import com.outsourcing.common.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Optional<Order> findByOrderId(@Param("id") Long id);

}
