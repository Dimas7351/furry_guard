package com.app.furryguard.entity.dto.userDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(name = "UserSignupDto")
public class UserSignupDto {

    private String firstName;

    private String surname;

    private String lastName;

    @Pattern(
            regexp = "^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Некорректный формат адреса электронной почты}"
    )
    @Schema(example = "dogWithPets@gmail.com")
    private String email;

    @Size(min = 7)
    private String password;

    private LocalDate dateOfBirth;

}
