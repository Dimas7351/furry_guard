package com.app.furryguard.entity.dto;

import com.app.furryguard.entity.Breed;
import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Информация для создания питомца")
public class GetPetDto {

    @Schema(description = "Кличка")
    private String name;

    @Schema(example = "Французский бульдог")
    private String breed;

    @Schema(description = "Пол животного")
    private Gender gender;

    @Schema(description = "Объект возраста")
    private AgeDto age;

    private LocalDate dateOfBirth;

    @Schema(description = "Вес животного в десятичной дроби", example = "3.53")
    private Double weight;

    @Schema(description = "Уровень активности животного")
    private ActivityLevel activityLevel;

    private String recommendations;

}
