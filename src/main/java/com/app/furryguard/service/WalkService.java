package com.app.furryguard.service;

import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.Walk;
import com.app.furryguard.entity.dto.AddWalkDto;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.mapper.WalkMapper;
import com.app.furryguard.repository.PetRepository;
import com.app.furryguard.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkService {

    private final WalkRepository walkRepository;
    private final WalkMapper walkMapper;
    private final PetRepository petRepository;

    @Transactional
    public Walk addWalk(AddWalkDto addWalkDto) {
        return walkRepository.save(walkMapper.mapToWalk(addWalkDto));
    }

    public List<Walk> getAllWalksByPetId(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new InvalidCredentialsException("Pet not found"));

        return walkRepository.findWalksByPetId(pet);
    }

}
