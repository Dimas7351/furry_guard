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
public class PetCreateDto {

    private String name;

    @Schema(example = "Французский бульдог")
    private String breed;
    private String gender;
    private Integer age;
    private Integer weight;
    private Integer activityLevel;

}
