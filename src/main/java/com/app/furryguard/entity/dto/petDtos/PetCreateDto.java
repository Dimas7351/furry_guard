package com.app.furryguard.entity.dto.petDtos;

import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Информация для создания питомца")
public class PetCreateDto {

    @Schema(description = "Кличка")
    private String name;

    @Schema(example = "Французский бульдог")
    private String breed;

    @Schema(description = "Пол животного", allowableValues = {"М", "Ж"})
    private Gender gender;

    @Schema(description = "Объект возраста")
    private AgeDto age;

    private LocalDate dateOfBirth;

    @Schema(description = "Вес животного в десятичной дроби", example = "3.53")
    private Double weight;

    @Schema(description = "Уровень активности животного", allowableValues = {"LOW", "MEDIUM", "HIGH"})
    private ActivityLevel activityLevel;

}
