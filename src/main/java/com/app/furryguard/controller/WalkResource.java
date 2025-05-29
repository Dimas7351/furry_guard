package com.app.furryguard.controller;

import com.app.furryguard.entity.Walk;
import com.app.furryguard.entity.dto.AddWalkDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "WalkController", description = "Операции, связанные с прогулками питомцев")
@RequestMapping("/walk")
public interface WalkResource {

    @Operation(
            summary = "Добавление совершенной прогулки",
            description = "Возвращает информацию о совершенной прогулке"
    )
    @PostMapping
    Walk addWalk(@RequestBody @Valid AddWalkDto addWalkDto);

    @Operation(
            summary = "Получение всех прогулок питомца по id",
            description = "Возвращает все прогулки определенного питомца"
    )
    @GetMapping("/{petId}")
    List<Walk> getAllWalksByPetId(@PathVariable Long petId);

}
