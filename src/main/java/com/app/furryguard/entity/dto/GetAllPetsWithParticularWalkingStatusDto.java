package com.app.furryguard.entity.dto;

import com.app.furryguard.entity.User;
import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.enums.Gender;
import com.app.furryguard.enums.PetWalkingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Информация о питомце и новом статусе")
public class GetAllPetsWithParticularWalkingStatusDto {

    @Schema(description = "ID питомца", example = "1")
    private Long petId;

    @Schema(description = "Кличка")
    private String name;

    @Schema(description = "Статус питомца", allowableValues = {"WANT_TO_WALK", "WANT_HOME"})
    private PetWalkingStatus petWalkingStatus;

    @Schema(example = "Французский бульдог")
    private String breed;

    @Schema(description = "Пол животного")
    private Gender gender;

    @Schema(description = "Объект возраста")
    private AgeDto age;

    @Schema(description = "Дата рождения")
    private LocalDate dateOfBirth;

    @Schema(description = "Уровень активности животного")
    private ActivityLevel activityLevel;

    @Schema(description = "Владелец питомца")
    private UserShortDto user;

}
