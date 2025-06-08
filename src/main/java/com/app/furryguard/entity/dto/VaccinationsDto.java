package com.app.furryguard.entity.dto;

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
public class VaccinationsDto {

    @Schema(description = "Заголовок для общей информации")
    private String commonHeader;

    @Schema(description = "Общая информация")
    private String common;

    @Schema(description = "Заголовок предыдущих прививок")
    private String previousHeader;

    @Schema(description = "Предыдущие прививки")
    private String previous;

    @Schema(description = "Заголовок текущих прививок")
    private String currentHeader;

    @Schema(description = "Текущие прививки")
    private String current;

    @Schema(description = "Заголовок будущих прививок")
    private String nextHeader;

    @Schema(description = "Будущие прививки")
    private String next;

}
