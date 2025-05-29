package com.app.furryguard.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Schema(description = "Таблица с прогулками каждого животного")
@Table(name = "walks")
public class Walk {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    @JsonBackReference
    private Pet petId;

    @Column(name = "date_time", nullable = false)
    @Schema(description = "Дата и время в формате Instant (timestampz)")
    private Instant dateTime;

    @Column(name = "duration", nullable = false)
    @Schema(description = "Длительность прогулки в минутах")
    private Integer duration;

}
