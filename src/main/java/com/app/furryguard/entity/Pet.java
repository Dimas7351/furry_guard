package com.app.furryguard.entity;


import com.app.furryguard.entity.dto.AgeDto;
import com.app.furryguard.enums.PetWalkingStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "weight")
    private Double weight;

    @Embedded
    private AgeDto age;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "activity_level")
    private String activityLevel;

    @Column(name = "recommendations")
    private String recommendations;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_walking_status")
    private PetWalkingStatus petWalkingStatus;

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
    private List<Walk> walks;

    @OneToMany(mappedBy = "petId")
    @JsonManagedReference
    private List<File> files;

}
