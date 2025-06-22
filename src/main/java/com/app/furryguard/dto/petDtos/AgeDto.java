package com.app.furryguard.dto.petDtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
@Schema(description = "Информация о возрасте питомца")
public class AgeDto {

    @Schema(example = "3")
    private Integer year;

    @Schema(example = "7")
    private Integer month;

    @Schema(example = "3")
    private Integer week;

}
