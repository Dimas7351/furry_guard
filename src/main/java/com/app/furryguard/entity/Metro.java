package com.app.furryguard.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "metro")
@Schema(description = "Таблица со списком метро")
public class Metro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            name = "id",
            example = "31", requiredMode = Schema.RequiredMode.REQUIRED,
                description = "Unique metro identifier."
    )
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(
            name = "Название метро",
            example = "TSKA", requiredMode = Schema.RequiredMode.REQUIRED,
                description = "Unique metro name."
    )
    private String name;
}
