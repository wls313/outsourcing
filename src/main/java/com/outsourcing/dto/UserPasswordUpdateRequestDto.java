package com.outsourcing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserPasswordUpdateRequestDto {

    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(min = 8, max = 12)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z0-9!@#$%^&*(),.?\":{}|<>]+$",
            message = "영문 대소문자, 숫자, 특수문자를 각각 최소 1글자씩 포함해야 합니다.")
    private String newPassword;

    public UserPasswordUpdateRequestDto(String oldPassword, String newPassword){
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }



}
