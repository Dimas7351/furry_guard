package com.app.furryguard.entity;


import com.app.furryguard.enums.ActivityLevel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "breeds")
@Schema(description = "Breed Entity")
public class Breed {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            name = "id",
            example = "141",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Unique breed identifier."
    )
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 100, message = "Name should be between 1 and 100 characters.")
    @Schema(
            name = "name",
            example = "Pit-bull",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Breed name. Should be between 1 and 100 characters."
    )
    private String name;

    @Column(name = "avg_activity", nullable = false)
    @Size(min = 1, max = 50, message = "Average activity should be between 1 and 50 characters.")
    @NotBlank(message = "Average activity cannot be blank.")
    @Schema(
        name = "avgActivity",
        example = "High",
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Average activity level. Examples: Low, Medium, High."
    )
    private String avgActivity;

    @Column(name = "overweight_risk")
    @Schema(
        example = "0.25",
        description = "Risk of overweight as a value between 0 and 1. Optional."
    )
    private Double overweightRisk;

    @JsonManagedReference
    @OneToMany(mappedBy = "breedId")
    private List<Pet> pets;

    @OneToMany(mappedBy = "breedId")
    @JsonManagedReference
    private List<BreedWeight> breedWeights;
}