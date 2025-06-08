package com.app.furryguard.entity;


import com.app.furryguard.enums.ActivityLevel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "breeds")
public class Breed {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "avg_activity", nullable = false)
    private String avgActivity;

    @JsonManagedReference
    @OneToMany(mappedBy = "breedId")
    private List<Pet> pets;

    @OneToMany(mappedBy = "breedId")
    @JsonManagedReference
    private List<BreedWeight> breedWeights;
}
