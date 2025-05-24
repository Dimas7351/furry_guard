package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.PetResource;
import com.app.furryguard.entity.Breed;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.dto.GetPetDto;
import com.app.furryguard.entity.dto.PetCreateDto;
import com.app.furryguard.service.PetService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pet")
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

}
