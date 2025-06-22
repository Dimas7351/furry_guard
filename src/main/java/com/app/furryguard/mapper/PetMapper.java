package com.app.furryguard.mapper;


import com.app.furryguard.entity.Pet;
import com.app.furryguard.dto.petDtos.GetAllPetsWithParticularWalkingStatusDto;
import com.app.furryguard.enums.ActivityLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
