package com.outsourcing.service.review;

import com.outsourcing.common.entity.Review.Review;
import com.outsourcing.common.entity.order.Order;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.common.exception.review.ReviewUserIdException;
import com.outsourcing.common.exception.review.ReviewUserNameException;
import com.outsourcing.dto.review.ReviewDto;
import com.outsourcing.dto.review.ReviewRequestDto;
import com.outsourcing.dto.review.ReviewStoreResponseDto;
import com.outsourcing.dto.review.ReviewUserResponseDto;
import com.outsourcing.repository.order.OrderRepository;
import com.outsourcing.repository.user.UserRepository;
import com.outsourcing.repository.review.ReviewRepository;
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

    public ReviewDto createReview(Long id,ReviewRequestDto requestDto) {
        Order order = orderRepository.findByOrderId(id)
                .orElseThrow(() -> new ReviewUserIdException(id));

        if(order == null || !order.getStatus().equals("DELIVERY_COMPLETED")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"no");
        }

        User user = userRepository.findByName(order.getUserName())
                .orElseThrow(() -> new ReviewUserNameException(order.getUserName()));

        Review review = Review.create(requestDto.rating(),requestDto.contents(),order.getUserName(),order.getStoreName(),order.getMenuName(),user, order.getId());

        reviewRepository.save(review);

        return ReviewDto.from(review);
    }

    public List<ReviewStoreResponseDto> findByStoreName(String storeName) {
        return reviewRepository.findByStoreName(storeName)
                .stream()
                .map(ReviewStoreResponseDto::toDto)
                .toList();
    }

    public List<ReviewUserResponseDto> findByUserName(String userName) {
        return reviewRepository.findByUserName(userName)
                .stream()
                .map(ReviewUserResponseDto::toDto)
                .toList();
    }

    @Transactional
    public ReviewStoreResponseDto updateReview(Long id, Double rating, String contents) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new ReviewUserIdException(id));

        review.updateReview(rating,contents);

        return new ReviewStoreResponseDto(review.getRating(),review.getMenuName(), review.getUserName(), review.getContents(), review.getCreateAt());
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
