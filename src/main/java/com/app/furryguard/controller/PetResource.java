package com.app.furryguard.controller;

import com.app.furryguard.entity.Breed;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.GetPetDto;
import com.app.furryguard.entity.dto.PetCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PetController", description = "Операции, связанные с питомцами")
@RequestMapping("/pet")
public interface PetResource {

    @Operation(
            summary = "Добавление домашнего питомца",
            description = "Возвращает информацию о созданном питомце"
    )
    @PostMapping
    Pet createPet(@Valid
    PetCreateDto petCreateDto,
    BindingResult bindingResult) throws BindException;

    @Operation(
            summary = "Получение домашнего питомца",
            description = "Возвращает информацию о питомце по petId"
    )
    @GetMapping("/{petId}")
    GetPetDto getPet(@PathVariable Long petId);

    @Operation(
            summary = "Поиск породы",
            description = "Поиск породы по одному или нескольким символам"
    )
    @GetMapping("/searchBreed")
    List<String> searchBreedByPattern(@RequestParam String pattern);

}
