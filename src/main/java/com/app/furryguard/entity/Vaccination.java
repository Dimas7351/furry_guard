package com.app.furryguard.entity;


import com.app.furryguard.enums.VaccinationType;
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
@Table(name = "vaccinations")
public class Vaccination {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "vaccination_type", nullable = false)
    private VaccinationType vaccinationType;

    @Column(name = "start_weeks", nullable = false)
    private Integer startWeeks;

    @Column(name = "end_weeks", nullable = false)
    private Integer endWeeks;

    @Column(name = "revactination_weeks", nullable = false)
    private String revactinationWeeks;
}
