package com.outsourcing.dto;

import com.outsourcing.common.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    //이메일
    //이름
    //포지션
    private Long id;

    private String name;

    private String email;

    private String role;

    public UserResponseDto(Long id, String name, String email, String role){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

}
