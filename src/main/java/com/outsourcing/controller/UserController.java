package com.outsourcing.controller;

import com.outsourcing.dto.UserRequestDto;
import com.outsourcing.dto.UserResponseDto;
import com.outsourcing.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(
            @Valid
            @RequestBody UserRequestDto dto
    ){
        UserResponseDto userCreateResponseDto =
                userService.signup(
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getRole()
                );
        return new ResponseEntity<>(userCreateResponseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequestDto dto
    ){
        UserResponseDto updateUser = userService.updateUser(dto.getName(), dto.getEmail());

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

}
