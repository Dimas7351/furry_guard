package com.app.furryguard.entity.dto.petDtos;

import com.app.furryguard.entity.dto.VaccinationsDto;
import com.app.furryguard.entity.dto.fileDtos.GetFileDto;
import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.enums.Gender;
import com.app.furryguard.enums.PetWalkingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Информация для создания питомца")
public class UpdatePetDto {


    @Schema(description = "Объект возраста")
    private AgeDto age;

    private LocalDate dateOfBirth;

    @Schema(description = "Вес животного в десятичной дроби", example = "3.53")
    private Double weight;

    @Schema(description = "Количество граммов корма в день")
    private Integer feed;

    @Schema(description = "Уровень активности животного", allowableValues = {"LOW", "MEDIUM", "HIGH"})
    private ActivityLevel activityLevel;

    @Schema(description = "Точное время прогулки в минутах")
    private Integer exactActivity;

}
