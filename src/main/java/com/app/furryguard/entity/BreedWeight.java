package com.app.furryguard.entity;


import com.app.furryguard.enums.ActivityLevel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "breed_weights")
public class BreedWeight {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_age_months", nullable = false)
    private Integer minAgeMonths;

    @Column(name = "max_age_months", nullable = false)
    private Integer maxAgeMonths;

    @Column(name = "min_weight_kg", nullable = false)
    private Integer minWeightKg;

    @Column(name = "max_weight_kg", nullable = false)
    private Integer maxWeightKg;

    @ManyToOne
    @JoinColumn(name = "breed_id", referencedColumnName = "id")
    @JsonBackReference
    private Breed breedId;
}
