package com.app.furryguard.dto.userDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Schema
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserLoginDto {

    @Schema(example = "dogWithPets@gmail.com")
    @Email(message = "Некорректный формат адреса электронной почты")
    private String email;

    @Size(min = 7, max = 100, message = "Password should be between 7 and 100 characters.")
    private String password;

}
