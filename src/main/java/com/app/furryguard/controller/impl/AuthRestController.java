package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.AuthResource;
import com.app.furryguard.entity.dto.userDtos.UserLoginDto;
import com.app.furryguard.entity.dto.userDtos.UserSignupDto;
import com.app.furryguard.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthRestController implements AuthResource {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {

        return authService.login(userLoginDto);

    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserSignupDto userSignupDto) {

        return authService.signup(userSignupDto);
    }

}
