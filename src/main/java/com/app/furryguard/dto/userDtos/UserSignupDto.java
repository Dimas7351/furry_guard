package com.app.furryguard.dto.userDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
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

    @Email(message = "Некорректный формат адреса электронной почты")
    @Schema(example = "dogWithPets@gmail.com")
    private String email;

    @Size(min = 7, max = 100, message = "Password should be between 7 and 100 characters.")
    private String password;

    @Past
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
}
