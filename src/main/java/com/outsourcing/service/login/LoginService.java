package com.outsourcing.service.login;

import com.outsourcing.common.config.PasswordEncoder;
import com.outsourcing.common.entity.user.User;
import com.outsourcing.dto.login.LoginResponseDto;
import com.outsourcing.repository.login.LoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto login(String email,String password) {
        Optional<User> byEmail = loginRepository.findByEmail(email);

        if(byEmail.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"아이디 또는 비밀번호가 잘못됐습니다");
        }

        User user = byEmail.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 잘못됐습니다");
        }

        return new LoginResponseDto(user.getEmail());
    }
}
