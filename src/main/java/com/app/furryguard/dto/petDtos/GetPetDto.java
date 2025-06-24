package com.app.furryguard.dto.petDtos;

import com.app.furryguard.dto.VaccinationsDto;
import com.app.furryguard.dto.fileDtos.GetFileDto;
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

    @Schema(description = "Рекомендации")
    private String recommendations;

    @Schema(description = "Признак присутствия рекомендаций")
    private Boolean hasRecommendations;

    @Schema(description = "График вакцинаций")
    private VaccinationsDto vaccinations;

    @Schema(description = "Статус желания прогулки", allowableValues = {"WANT_TO_WALK", "WANT_HOME"})
    private PetWalkingStatus petWalkingStatus;

    @Schema(description = "Количество граммов корма в день")
    private Integer feed;

    @Schema(description = "Точное время прогулки в минутах")
    private Integer exactActivity;

    @Schema(description = "Файлы питомца")
    private List<GetFileDto> files;

}
