package com.app.furryguard.entity;


import com.app.furryguard.enums.FileType;
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
@Schema(description = "Таблица с файлами")
@Table(name = "files")
public class File {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false)
    @Schema(description = "Название файла")
    private String fileName;

    @Column(name = "file_type", nullable = false)
    @Schema(description = "Тип файла", allowableValues = {"ANALYZE", "DOCTOR_CONCLUSION", "OTHER"})
    private FileType fileType;

    @Column(name = "create_date", nullable = false)
    @Schema(description = "Дата и время создания в формате Instant (timestampz)")
    private Instant createDate;

    @Column(name = "content", nullable = false)
    @Schema(description = "Файл, закодированный в base64")
    private String content;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    @JsonBackReference
    private Pet petId;

}
