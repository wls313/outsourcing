package com.outsourcing.controller.review;

import com.outsourcing.dto.review.ReviewDto;
import com.outsourcing.dto.review.ReviewRequestDto;
import com.outsourcing.dto.review.ReviewStoreResponseDto;
import com.outsourcing.dto.review.ReviewUserResponseDto;
import com.outsourcing.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{id}")
    public ResponseEntity<ReviewDto> create(@PathVariable Long id, @RequestBody ReviewRequestDto requestDto) {
        ReviewDto response = reviewService.createReview(id,requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<ReviewStoreResponseDto>> findByStoreId(@PathVariable Long id){
        List<ReviewStoreResponseDto> responseDtoList = reviewService.findByStoreId(id);

        return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReviewUserResponseDto>> findByUserId(@PathVariable Long id){
        List<ReviewUserResponseDto> responseDtoList = reviewService.findByUserId(id);

        return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReviewStoreResponseDto> updateReview(@PathVariable Long id,@RequestBody ReviewRequestDto requestDto){
        ReviewStoreResponseDto reviewStoreResponseDto = reviewService.updateReview(id,requestDto.rating(),requestDto.contents());

        return new ResponseEntity<>(reviewStoreResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewStoreResponseDto> delete(@PathVariable Long id){
        reviewService.deleteReview(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
