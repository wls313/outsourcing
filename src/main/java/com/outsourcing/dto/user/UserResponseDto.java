package com.outsourcing.dto.user;

import com.outsourcing.common.entity.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

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
