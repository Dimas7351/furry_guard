package com.app.furryguard.dto.userDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
            regexp = "^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Некорректный формат адреса электронной почты}"
    )
    private String email;

    private String password;

}
