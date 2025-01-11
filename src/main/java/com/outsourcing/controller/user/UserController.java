package com.outsourcing.controller.user;

import com.outsourcing.dto.user.UserCreateResponseDto;
import com.outsourcing.dto.user.UserPasswordUpdateRequestDto;
import com.outsourcing.dto.user.UserRequestDto;
import com.outsourcing.dto.user.UserResponseDto;
import com.outsourcing.service.user.UserService;
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
    public ResponseEntity<UserCreateResponseDto> signup(
            @Valid
            @RequestBody UserRequestDto dto
    ){
        UserCreateResponseDto userCreateResponseDto =
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

        //이메일 수정 불가.
        UserResponseDto updateUser = userService.updateUser(id, dto.getName());

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