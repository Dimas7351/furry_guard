package com.app.furryguard.controller;

import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.UserLoginDto;
import com.app.furryguard.entity.dto.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "AuthController", description = "Операции, связанные с регистрацией и авторизацией пользователей")
@RequestMapping("/auth")
public interface AuthResource {

    @Operation(
            summary = "Аутентификация пользователя",
            description =
                    "Выполняет аутентификацию по email и паролю. " +
                            "В случае успеха возвращает JWT токен."
    )
    @PostMapping("/login")
    String login(@RequestBody UserLoginDto userLoginDto);


    @Operation(
            summary = "Регистрация пользователя",
            description = "Выполняет регистрацию пользователя по логину, паролю и email."
    )
    @PostMapping("/signup")
    User signup(@RequestBody UserSignupDto userSignupDto);

}
