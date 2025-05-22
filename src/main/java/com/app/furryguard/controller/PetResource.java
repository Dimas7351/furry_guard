package com.app.furryguard.controller;

import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.PetCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "PetController", description = "Операции, связанные с питомцами")
@RequestMapping("/pet")
public interface PetResource {

    @Operation(
            summary = "Добавление домашнего питомца",
            description = "Возвращает информацию о созданном питомце"
    )
    @PostMapping
    Pet createPet(PetCreateDto petCreateDto);

    @Operation(
            summary = "Получение домашнего питомца",
            description = "Возвращает информацию о питомце по petId"
    )
    @GetMapping("/{petId}")
    Pet getPet(@PathVariable Long petId);

}
