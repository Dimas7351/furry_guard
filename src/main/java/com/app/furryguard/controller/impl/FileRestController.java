package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.FileResource;
import com.app.furryguard.entity.File;
import com.app.furryguard.dto.fileDtos.AddFileDto;
import com.app.furryguard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileRestController implements FileResource {

    private final FileService fileService;

    @Override
    public File addFile(AddFileDto addFileDto) {
        return fileService.addFile(addFileDto);
    }

    @Override
    public List<File> getAllFilesByPetId(Long petId) {
        return fileService.getAllFilesByPetId(petId);
    }

}
