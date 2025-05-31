package com.app.furryguard.entity.dto.fileDtos;

import com.app.furryguard.enums.FileType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Информация о запрашиваемых файлов определенного питомца")
public class GetFileDto {

    @Schema(description = "Название файла")
    private String fileName;

    @Schema(description = "Тип файла", allowableValues = {"ANALYZE", "DOCTOR_CONCLUSION", "OTHER"})
    private FileType fileType;

    @Schema(description = "Файл, закодированный в base64")
    private String content;

}
