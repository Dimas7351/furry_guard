package com.app.furryguard.service;

import com.app.furryguard.config.JwtTokenProvider;
import com.app.furryguard.entity.Breed;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.AgeDto;
import com.app.furryguard.entity.dto.GetPetDto;
import com.app.furryguard.entity.dto.PetCreateDto;
import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.enums.Gender;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.repository.BreedRepository;
import com.app.furryguard.repository.PetRepository;
import com.app.furryguard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final BreedRepository breedRepository;

    @Transactional
    public Pet createPet(PetCreateDto petCreateDto) {
        User user = userRepository.findUserByEmail(JwtTokenProvider.getCurrentUserEmail())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        Breed breed = breedRepository.findBreedByNameIgnoreCase(petCreateDto.getBreed())
                .orElseThrow(() -> new InvalidCredentialsException("Breed not found"));

        Pet pet = Pet.builder()
                .name(petCreateDto.getName())
                .weight(petCreateDto.getWeight())
                .gender(petCreateDto.getGender().name())
                .age(convertAgeToWeeks(petCreateDto.getAge()))
                .activityLevel(petCreateDto.getActivityLevel().name())
                .recommendations(generateRecommendations(petCreateDto))
                .breedId(breed)
                .ownerId(user)
                .build();

        return petRepository.save(pet);
    }

    public GetPetDto getPet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new InvalidCredentialsException("Pet not found"));

        return GetPetDto.builder()
                .name(pet.getName())
                .gender(Gender.valueOf(pet.getGender()))
                .age(convertToAgeDto(pet.getAge()))
                .breed(pet.getBreedId().getName())
                .weight(pet.getWeight())
                .activityLevel(ActivityLevel.valueOf(pet.getActivityLevel()))
                .recommendations(pet.getRecommendations())
                .build();
    }

    public List<String> searchBreedByPattern(String pattern){
        return breedRepository.findByNameContainingIgnoreCase(pattern).stream()
                .map(Breed::getName)
                .toList();
    }

    private String generateRecommendations(PetCreateDto petCreateDto){

        return String.format("У вашего питомца повышенный риск ожирения. " +
                "Рекомендуется снизить вес до %d кг. " +
                "Длительность ежедневных прогулок увеличить до %d минут в день. " +
                "Кормить питомца необходимо по %d г. гипоаллергенного корма 3 раза в день.",
                10, 90, 70);
    }

    private Integer convertAgeToWeeks(AgeDto ageDto){
        return (int) Math.round(ageDto.getYear()*52.18+ageDto.getWeek()*4.348125+ageDto.getWeek());
    }

    private AgeDto convertToAgeDto(Integer totalWeeks) {
        int years = totalWeeks / 52;
        int remainingWeeksAfterYears = totalWeeks % 52;

        int months = (int) (remainingWeeksAfterYears / 4.348);
        int weeks = (int) Math.round(remainingWeeksAfterYears % 4.348);

        return AgeDto.builder()
                .year(years)
                .month(months)
                .week(weeks)
                .build();
    }

}
