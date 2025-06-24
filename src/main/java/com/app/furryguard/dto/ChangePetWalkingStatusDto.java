package com.app.furryguard.dto;

import com.app.furryguard.enums.PetWalkingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Информация о питомце и новом статусе")
public class ChangePetWalkingStatusDto {

    @Schema(description = "ID питомца", example = "1")
    private Long petId;

    @Schema(description = "Статус питомца", allowableValues = {"WANT_TO_WALK", "WANT_HOME"})
    private PetWalkingStatus petWalkingStatus;

}
