package com.app.furryguard.controller;

import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UserController", description = "Операции, связанные с пользователями")
@RequestMapping("/user")
public interface UserResource {

    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает пользователя по данным из JWT",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping()
    User getUser();

    @Operation(
            summary = "Получение питомцев пользователя",
            description = "Возвращает питомцев пользователя по данным из JWT",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/myPets")
    List<Pet> getPets();

}
