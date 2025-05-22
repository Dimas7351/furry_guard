package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.UserResource;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserRestController implements UserResource {

    private final UserService userService;

    @GetMapping
    public User getUser() {
        return userService.getUser();
    }

    @GetMapping("/myPets")
    public List<Pet> getPets() {
        return userService.getPets();

    }

}
