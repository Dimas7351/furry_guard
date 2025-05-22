package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.PetResource;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.dto.PetCreateDto;
import com.app.furryguard.service.PetService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetRestController implements PetResource {

    private final PetService petService;

    @PostMapping
    public Pet createPet(PetCreateDto petCreateDto) {
        return petService.createPet(petCreateDto);
    }

    @GetMapping("/{petId}")
    public Pet getPet(@PathVariable Long petId) {
        return petService.getPet(petId);
    }

}
