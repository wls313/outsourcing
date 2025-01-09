package com.outsourcing.service;

import com.outsourcing.common.config.PasswordEncoder;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.common.exception.IdNotFoundExcetion;
import com.outsourcing.common.exception.PasswordMismatchException;
import com.outsourcing.common.exception.UnavailableIdOrPasswordException;
import com.outsourcing.dto.UserResponseDto;
import com.outsourcing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signup(String name, String email, String password, String role){

        String encodePassword = passwordEncoder.encode(password);

        if(DeleteUserService.isEmailDeleted(email)){
            throw new UnavailableIdOrPasswordException("이미 탈퇴한 아이디 입니다. ");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UnavailableIdOrPasswordException("이미 사용중인 이메일 입니다");
        }

        User user = new User(name, email, encodePassword, role);

        User saveUser = userRepository.save(user);

        return UserResponseDto.toDto(saveUser);

    }

    public List<UserResponseDto> findAllUsers(String role){

        List<User> findUser = userRepository.findByRole(role);

        return findUser.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());

    }

    public UserResponseDto findUserById(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new IdNotFoundExcetion("아이디를 확인해주세요");
        }

        User findUser = optionalUser.get();

        return UserResponseDto.toDto(findUser);

    }

    public UserResponseDto updateUser(Long id, String name, String email){

        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.updateUser(name, email);

        User updateUser = userRepository.save(findUser);

        return UserResponseDto.toDto(updateUser);

    }

    public void updateUserPassword(Long id, String oldPassword, String newPassword){

        User findUser = userRepository.findByIdOrElseThrow(id);

        if(oldPassword.equals(newPassword)){
            throw new UnavailableIdOrPasswordException("같은 비밀번호는 사용할 수 없습니다");
        }

        String encodePassword = passwordEncoder.encode(newPassword);

        if(!passwordEncoder.matches(oldPassword, findUser.getPassword())){
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        findUser.updatePassword(encodePassword);

        userRepository.save(findUser);


    }

    public void deleteUser(Long id, String password){

        User findUser = userRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(password, findUser.getPassword())){
            throw new PasswordMismatchException("비밀번호를 확인하세요");
        }

        DeleteUserService.addToList(findUser.getEmail());

        userRepository.delete(findUser);

    }




}
