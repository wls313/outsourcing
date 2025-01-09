package com.outsourcing.controller.login;

import com.outsourcing.common.config.Const;
import com.outsourcing.dto.login.LoginRequestDto;
import com.outsourcing.dto.login.LoginResponseDto;
import com.outsourcing.service.login.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ){
        LoginResponseDto responseDto = loginService.login(request.email(),request.password());

        if(responseDto.email() == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        HttpSession session = httpRequest.getSession();
        session.setAttribute(Const.LOGIN_USER, responseDto);

        Cookie cookie = new Cookie("email", responseDto.email());

        httpResponse.addCookie(cookie);

        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("email" , null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        httpResponse.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
