package com.app.furryguard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MetroController", description = "Операции, связанные с метро")
@RequestMapping("/metro")
public interface MetroResource {

    @Operation(
            summary = "Поиск метро",
            description = "Поиск метро по нескольким символам"
    )
    @GetMapping("/searchMetro")
    List<String> searchMetroByPattern(@RequestParam(required = false) String pattern);

}
