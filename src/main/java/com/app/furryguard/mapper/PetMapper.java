package com.app.furryguard.mapper;


import com.app.furryguard.entity.File;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.dto.AddFileDto;
import com.app.furryguard.entity.dto.GetAllPetsWithParticularWalkingStatusDto;
import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PetMapper {

    private final UserMapper userMapper;

    public GetAllPetsWithParticularWalkingStatusDto mapToGetAllPetsWithParticularWalkingStatusDto(Pet pet) {

        return GetAllPetsWithParticularWalkingStatusDto.builder()
                .petId(pet.getId())
                .name(pet.getName())
                .petWalkingStatus(pet.getPetWalkingStatus())
                .breed(pet.getBreedId().getName())
                .gender(pet.getGender())
                .age(pet.getAge())
                .dateOfBirth(pet.getDateOfBirth())
                .activityLevel(ActivityLevel.valueOf(pet.getActivityLevel()))
                .user(userMapper.mapToUserShortDto(pet.getOwnerId()))
                .build();

    }

}
