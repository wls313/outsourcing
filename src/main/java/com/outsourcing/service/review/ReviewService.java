package com.outsourcing.service.review;

import com.outsourcing.common.entity.Review.Review;
import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.order.OrderStatus;
import com.outsourcing.common.entity.store.Store;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.common.exception.review.ReviewUserIdException;
import com.outsourcing.common.exception.review.ReviewUserNameException;
import com.outsourcing.dto.review.ReviewDto;
import com.outsourcing.dto.review.ReviewRequestDto;
import com.outsourcing.dto.review.ReviewStoreResponseDto;
import com.outsourcing.dto.review.ReviewUserResponseDto;
import com.outsourcing.repository.order.OrderRepository;
import com.outsourcing.repository.review.ReviewRepository;
import com.outsourcing.repository.store.StoreRepository;
import com.outsourcing.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewDto createReview(Long id,ReviewRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ReviewUserIdException(id));

        if(order == null || !order.getStatus().equals(OrderStatus.DELIVERY_COMPLETED)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"no");
        }

        User user = userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new ReviewUserNameException(order.getUser().getId()));

        Store store = storeRepository.findById(order.getStore().getId())
                .orElseThrow(() -> new ReviewUserNameException(order.getUser().getId()));

        Review review = Review.create(requestDto.rating(),requestDto.contents(),user, order, store);

        reviewRepository.save(review);

        return ReviewDto.from(review);
    }

    public List<ReviewStoreResponseDto> findByStoreId(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ReviewUserIdException(id));

        return reviewRepository.findByStoreId(order.getStore().getId())
                .stream()
                .map(ReviewStoreResponseDto::toDto)
                .toList();
    }

    public List<ReviewUserResponseDto> findByUserId(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ReviewUserIdException(id));

        return reviewRepository.findByUserId(order.getUser().getId())
                .stream()
                .map(ReviewUserResponseDto::toDto)
                .toList();
    }

    @Transactional
    public ReviewStoreResponseDto updateReview(Long id, Double rating, String contents) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new ReviewUserIdException(id));

        review.updateReview(rating,contents);

        return ReviewStoreResponseDto.toDto(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}