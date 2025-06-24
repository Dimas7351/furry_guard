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
@Schema(description = "Информация о рисках")
public class GetRiskDto {

    private Double weightRisk;
    private Double breedRisk;
    private Double feedRisk;
    private Double activityRisk;
    private Double ageRisk;
    private Double integralRisk;

}
