package com.app.furryguard.mapper;


import com.app.furryguard.entity.File;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.dto.fileDtos.AddFileDto;
import com.app.furryguard.dto.fileDtos.GetFileDto;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class FileMapper {

    private final PetRepository petRepository;

    public GetFileDto mapToGetFileDto(File file) {

        return GetFileDto.builder()
                .fileName(file.getFileName())
                .fileType(file.getFileType())
                .content(file.getContent())
                .build();
    }

    public File mapToFile(AddFileDto addFileDto) {
        Pet pet = petRepository.findById(addFileDto.getPetId())
                .orElseThrow(() -> new InvalidCredentialsException("Pet not found"));

        return File.builder()
                .petId(pet)
                .fileName(addFileDto.getFileName())
                .fileType(addFileDto.getFileType())
                .createDate(Instant.now())
                .content(addFileDto.getContent())
                .build();
    }

}
