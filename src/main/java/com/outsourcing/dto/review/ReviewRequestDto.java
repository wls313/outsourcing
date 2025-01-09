package com.outsourcing.dto.review;

import jakarta.validation.constraints.NotBlank;

public record ReviewRequestDto (
        @NotBlank
        Double rating,
        @NotBlank
        String contents
){
}
