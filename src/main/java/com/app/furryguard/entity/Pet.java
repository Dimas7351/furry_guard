package com.app.furryguard.entity;


import com.app.furryguard.dto.petDtos.AgeDto;
import com.app.furryguard.enums.Gender;
import com.app.furryguard.enums.PetWalkingStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
@Schema(description = "Pet Entity")
public class Pet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            name = "id", example = "111",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Unique Pet identifier."
    )
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "name can't be empty")
    @Size(min = 1, max = 100, message = "name should be between 1 and 100 characters.")
    @Schema(
            name = "name",
            example = "Luci",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Name. Should be between 1 and 100 characters."
    )
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    @NotNull(message = "Gender cannot be null")
    @Schema(name = "gender", example = "MALE", description = "Pet gender. Options: MALE, FEMALE.")
    private Gender gender;

    @Column(name = "weight")
    @DecimalMin(value = "0.000", message = "Weight must be positive.")
    @DecimalMax(value = "99.999", message = "Weight must not exceed 99.999.")
    @Schema(
        example = "12.345",
        description = "Weight in kilograms. Must be between 0.000 and 99.999."
    )
    private Double weight;

    @Embedded
    private AgeDto age;

    @Column(name = "date_of_birth")
    @Past(message = "Date of birth must be in the past.")
    @Schema(
        example = "2015-08-25",
        description = "Birth date of the pet (if applicable). Must be a past date."
    )
    private LocalDate dateOfBirth;

    @Column(name = "activity_level")
    @Size(max = 10, message = "Activity level must not exceed 10 characters.")
    @Schema(
            name = "activityLevel",
            example = "HIGH",
            description = "Activity level. Max 10 characters."
    )
    private String activityLevel;

    @Column(name = "feed")
    private Integer feed;

    @Column(name = "recommendations")
    //TODO @Schema(!*--*!)
    private String recommendations;

    @Column(name = "has_recommendations")
    @Schema(
            description = "Статус присутствия рекомендаций: true - есть рекомендации" +
            "(статус - Есть рекомендации), false (Здоров)")
    private boolean hasRecommendations;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_walking_status")
    @Schema(example = "ACTIVE", description = "Pet walking status. E.g. Wants to walk.")
    private PetWalkingStatus petWalkingStatus;

    @Column(name = "exact_activity")
    private Integer exactActivity;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonBackReference
    private User ownerId;

    @ManyToOne
    @JoinColumn(name = "breed_id", referencedColumnName = "id")
    @JsonBackReference
    private Breed breedId;

    @OneToMany(mappedBy = "petId")
    @JsonManagedReference
    private List<File> files;
}
