package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.AuthResource;
import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.UserLoginDto;
import com.app.furryguard.entity.dto.UserSignupDto;
import com.app.furryguard.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public User signup(@RequestBody UserSignupDto userSignupDto) {

        return authService.signup(userSignupDto);
    }

}
