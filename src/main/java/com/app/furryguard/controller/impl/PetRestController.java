package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.PetResource;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.dto.*;
import com.app.furryguard.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PetRestController implements PetResource {

    private final PetService petService;

    @PostMapping
    public Pet createPet(@Valid @RequestBody PetCreateDto petCreateDto,
                         BindingResult bindingResult) throws BindException {

        log.info("Вход в контроллер createPet");

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            }

            throw new BindException(bindingResult);
        }

        return petService.createPet(petCreateDto);
    }

    @GetMapping("/{petId}")
    public GetPetDto getPet(@PathVariable Long petId) {
        return petService.getPet(petId);
    }

    @Override
    public List<String> searchBreedByPattern(String pattern) {
        return petService.searchBreedByPattern(pattern);
    }

    @Override
    public WalkingAnswerDto changePetWalkingStatus(ChangePetWalkingStatusDto changePetWalkingStatusDto) {
        return petService.changePetWalkingStatus(changePetWalkingStatusDto);
    }

    @Override
    public List<Pet> findPetsWithParticularWalkingStatus(WalkingStatusDto walkingStatusDto) {
        return petService.findPetsWithParticularWalkingStatus(walkingStatusDto);
    }

}
