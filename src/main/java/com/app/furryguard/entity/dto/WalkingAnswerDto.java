package com.app.furryguard.entity.dto;

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
public class WalkingAnswerDto {

    @Schema(description = "ID питомца", example = "1")
    private String message;

}
