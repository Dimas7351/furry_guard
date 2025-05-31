package com.app.furryguard.service;

import com.app.furryguard.entity.File;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.dto.fileDtos.AddFileDto;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.mapper.FileMapper;
import com.app.furryguard.repository.FileRepository;
import com.app.furryguard.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;
    private final PetRepository petRepository;
    private final FileMapper fileMapper;

    @Transactional
    public File addFile(AddFileDto addFileDto) {
        return fileRepository.save(fileMapper.mapToFile(addFileDto));
    }

    public List<File> getAllFilesByPetId(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new InvalidCredentialsException("Pet not found"));

        return fileRepository.getAllFilesByPetId(pet);
    }

}
