package com.outsourcing.service.review;

import com.outsourcing.common.entity.Order;
import com.outsourcing.common.entity.Review;
import com.outsourcing.common.entity.User;
import com.outsourcing.dto.review.ReviewDto;
import com.outsourcing.dto.review.ReviewRequestDto;
import com.outsourcing.repository.OrderRepository;
import com.outsourcing.repository.UserRepository;
import com.outsourcing.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public ReviewDto createReview(Long id,ReviewRequestDto requestDto) {
        Order order = orderRepository.findByOrderId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        User user = userRepository.findByName(order.getMemberName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        Review review = Review.create(requestDto.rating(),requestDto.contents(),order.getMemberName(),order.getMenuName(),user, order.getId());

        reviewRepository.save(review);

        return ReviewDto.from(review);
    }
}
