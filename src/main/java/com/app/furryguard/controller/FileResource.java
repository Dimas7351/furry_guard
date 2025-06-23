package com.app.furryguard.controller;

import com.app.furryguard.entity.File;
import com.app.furryguard.dto.fileDtos.AddFileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FileController", description = "Операции, связанные с файлами")
@RequestMapping("/api/v1/file")
public interface FileResource {

    @Operation(
            summary = "Добавление нового файла",
            description = "Добавляет файл к определенному питомцу"
    )
    @PostMapping
    File addFile(@RequestBody @Valid AddFileDto addFileDto);

    @Operation(
            summary = "Получение всех файлов питомца по id",
            description = "Возвращает все файлы определенного питомца"
    )
    @GetMapping("/{petId}")
    List<File> getAllFilesByPetId(@PathVariable Long petId);

}
