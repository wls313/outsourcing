package com.outsourcing.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
