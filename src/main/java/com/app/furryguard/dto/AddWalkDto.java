package com.app.furryguard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Информация о совершенной прогулке")
public class AddWalkDto {

    @Schema(description = "ID питомца", example = "1")
    private Long petId;

    @Schema(description = "Дата и время прогулки")
    private Instant dateTime;

    @Schema(description = "Длительность прогулки (в минутах)", example = "50")
    private Integer duration;

}
