package com.outsourcing.service.user;

import com.outsourcing.common.config.PasswordEncoder;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.common.exception.user.ArgumentMismatchException;
import com.outsourcing.common.exception.user.NotFoundException;
import com.outsourcing.common.exception.user.UnavailableIdOrPasswordException;
import com.outsourcing.dto.user.UserCreateResponseDto;
import com.outsourcing.dto.user.UserResponseDto;
import com.outsourcing.repository.user.UserRepository;
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

    public UserCreateResponseDto signup(String name, String email, String password, String role){

        String encodePassword = passwordEncoder.encode(password);

        Optional<User> findUser = userRepository.findByEmail(email);

        if(findUser.isPresent()) {
            User user = findUser.get();
            if (!user.isStatus()) {
                throw new UnavailableIdOrPasswordException("이미 탈퇴한 아이디 입니다. ");
            }
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UnavailableIdOrPasswordException("이미 사용중인 이메일 입니다");
        }

        User user = new User(name, email, encodePassword, role);

        User saveUser = userRepository.save(user);

        return UserCreateResponseDto.createDto(saveUser);

    }

    public List<UserResponseDto> findAllUsers(String role){

        List<User> findUser = userRepository.findByRole(role);

        return findUser.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                        ))
                .collect(Collectors.toList());

    }

    public UserResponseDto findUserById(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new NotFoundException("아이디를 확인해주세요");
        }

        User findUser = optionalUser.get();

        return UserResponseDto.toDto(findUser);

    }

    public UserResponseDto updateUser(Long id, String name){

        User findUser = findByIdOrElseThrow(id);

        findUser.updateUser(name);

        User updateUser = userRepository.save(findUser);

        return UserResponseDto.toDto(updateUser);

    }

    public void updateUserPassword(Long id, String oldPassword, String newPassword){

        User findUser = findByIdOrElseThrow(id);

        if(oldPassword.equals(newPassword)){
            throw new UnavailableIdOrPasswordException("같은 비밀번호는 사용할 수 없습니다");
        }

        String encodePassword = passwordEncoder.encode(newPassword);

        if(!passwordEncoder.matches(oldPassword, findUser.getPassword())){
            throw new ArgumentMismatchException("비밀번호가 일치하지 않습니다.");
        }

        findUser.updatePassword(encodePassword);

        userRepository.save(findUser);


    }

    public void deleteUser(Long id, String password){

        User findUser = findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(password, findUser.getPassword())){
            throw new ArgumentMismatchException("비밀번호를 확인하세요");
        }

        findUser.updateUserStatus(false);

        userRepository.save(findUser);

    }

    public User findByIdOrElseThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("아이디를 확인해주세요"));
    }


}
