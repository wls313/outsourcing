package com.outsourcing.controller.review;

import com.outsourcing.dto.review.ReviewDto;
import com.outsourcing.dto.review.ReviewRequestDto;
import com.outsourcing.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{id}")
    public ResponseEntity<ReviewDto> create(@PathVariable Long id, @RequestBody ReviewRequestDto requestDto) {
        ReviewDto response = reviewService.createReview(id,requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
