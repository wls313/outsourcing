package com.outsourcing.controller;

import com.outsourcing.dto.UserPasswordUpdateRequestDto;
import com.outsourcing.dto.UserRequestDto;
import com.outsourcing.dto.UserResponseDto;
import com.outsourcing.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{role}")
    public ResponseEntity<List<UserResponseDto>> findAllUsers(@PathVariable String role){

        List<UserResponseDto> userResponseDto = userService.findAllUsers(role);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }

    @GetMapping("/{role}/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){

        UserResponseDto userResponseDto = userService.findUserById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }

    @PatchMapping("/update/{role}/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto dto
    ){

        UserResponseDto updateUser = userService.updateUser(id, dto.getName(), dto.getEmail());

        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }

    @PatchMapping("/updatepassword/{role}/{id}")
    public ResponseEntity<UserResponseDto> updateUserPassword(
            @PathVariable Long id,
            @Valid
            @RequestBody UserPasswordUpdateRequestDto dto
    ){

        userService.updateUserPassword(id, dto.getOldPassword(), dto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{role}/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto dto
    ){

        userService.deleteUser(id, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}


