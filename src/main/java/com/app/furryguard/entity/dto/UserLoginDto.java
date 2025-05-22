package com.app.furryguard.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String email;

    private String password;

}
