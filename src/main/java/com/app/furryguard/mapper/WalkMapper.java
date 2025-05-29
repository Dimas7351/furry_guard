package com.app.furryguard.mapper;


import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.Walk;
import com.app.furryguard.entity.dto.AddWalkDto;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalkMapper {

    private final PetRepository petRepository;

    public Walk mapToWalk(AddWalkDto addWalkDto) {
        Pet pet = petRepository.findById(addWalkDto.getPetId())
                .orElseThrow(() -> new InvalidCredentialsException("Pet not found"));

        return Walk.builder()
                .petId(pet)
                .dateTime(addWalkDto.getDateTime())
                .duration(addWalkDto.getDuration())
                .build();
    }

}
