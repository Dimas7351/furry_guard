package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.WalkResource;
import com.app.furryguard.entity.Walk;
import com.app.furryguard.entity.dto.AddWalkDto;
import com.app.furryguard.service.WalkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WalkRestController implements WalkResource {

    private final WalkService walkService;

    @PostMapping
    public Walk addWalk(AddWalkDto addWalkDto) {
        return walkService.addWalk(addWalkDto);
    }

    @Override
    public List<Walk> getAllWalksByPetId(Long petId) {
        return walkService.getAllWalksByPetId(petId);
    }


}
