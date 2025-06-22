package com.app.furryguard.entity;


import com.app.furryguard.enums.VaccinationType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vaccinations")
@Schema(description = "Vaccination Entity")
public class Vaccination {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            name = "id", example = "1231",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Unique Vaccine identifier."
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vaccination type is required.")
    @Column(name = "vaccination_type", nullable = false)
    @Schema(
            name = "VaccinationType",
            example = "RABIES",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Type of vaccination, e.g., Инфекционный гепатит, Чума плотоядных."
    )
    private VaccinationType vaccinationType;

    @Column(name = "start_weeks", nullable = false)
    @NotNull(message = "Start weeks is required.")
    @Min(value = 0, message = "Start weeks must be 0 or greater.")
    @Schema(
            name = "startWeeks",
            example = "8",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Starting week for the vaccination."
    )
    private Integer startWeeks;

    @NotNull(message = "End weeks is required.")
    @Min(value = 0, message = "End weeks must be 0 or greater.")
    @Column(name = "end_weeks", nullable = false)
        @Schema(
            name = "endweeks",
            example = "8",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Ending week for the vaccination."
    )
    private Integer endWeeks;

    @NotBlank(message = "Revaccination weeks must not be empty.")
    @Column(name = "revactination_weeks", nullable = false) //revaccination
    @Schema(
            name = "revaccinationWeeks",
            example = "12",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Ending week for the vaccination."
    )
    private String revactinationWeeks;
}
