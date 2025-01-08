package com.outsourcing.service;

import com.outsourcing.common.PasswordEncoder;
import com.outsourcing.common.entity.User;
import com.outsourcing.dto.UserRequestDto;
import com.outsourcing.dto.UserResponseDto;
import com.outsourcing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signup(String name, String email, String password, String role){

        String encodePassword = passwordEncoder.encode(password);

        User user = new User(name, email, encodePassword, role);

        User saveUser = userRepository.save(user);

        return UserResponseDto.toDto(saveUser);

    }

    public UserRequestDto
}
