package com.outsourcing.dto.user;

import com.outsourcing.common.entity.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class UserCreateResponseDto {

    private Long id;

    private String name;

    private String email;

    private String role;

    private String createDate;

    public UserCreateResponseDto(Long id, String name, String email, String role, String createDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.createDate = createDate;
    }

    public static UserCreateResponseDto createDto(User user){

        return new UserCreateResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                createdDate(user.getCreateAt())
        );
    }

    public static String createdDate(LocalDateTime createDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createDate.format(formatter);
    }


}
