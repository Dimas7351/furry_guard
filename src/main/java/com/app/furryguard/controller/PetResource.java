package com.app.furryguard.controller;

import com.app.furryguard.dto.ChangePetWalkingStatusDto;
import com.app.furryguard.dto.WalkingAnswerDto;
import com.app.furryguard.dto.WalkingStatusDto;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.dto.petDtos.GetAllPetsWithParticularWalkingStatusDto;
import com.app.furryguard.dto.petDtos.GetPetDto;
import com.app.furryguard.dto.petDtos.PetCreateDto;
import com.app.furryguard.dto.petDtos.UpdatePetDto;
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
            summary = "Изменение данных о питмоце",
            description = "Изменение данных, требующихся для генерации рекомендаций"
    )
    @PostMapping("/update/{petId}")
    Pet updatePet(@PathVariable Long petId,
                  @RequestBody UpdatePetDto updatePetDto);

    @Operation(
            summary = "Поиск породы",
            description = "Поиск породы по одному или нескольким символам"
    )
    @GetMapping("/searchBreed")
    List<String> searchBreedByPattern(@RequestParam(required = false) String pattern);

    @Operation(
            summary = "Смена статуса желания прогулки",
            description = "Изменяет статус желания питомца гулять"
    )
    @PostMapping("/walkingStatus")
    WalkingAnswerDto changePetWalkingStatus(@RequestBody ChangePetWalkingStatusDto changePetWalkingStatusDto);

    @Operation(
            summary = "Поиск всех питомцев с определенным статусом прогулки",
            description = "Поиск питомцев у которых есть определенный статус прогулки"
    )
    @PostMapping("/allWithStatus")
    List<GetAllPetsWithParticularWalkingStatusDto> findPetsWithParticularWalkingStatus(@RequestBody WalkingStatusDto walkingStatusDto);

}
