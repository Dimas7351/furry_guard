package com.app.furryguard.service;

import com.app.furryguard.config.JwtTokenProvider;
import com.app.furryguard.entity.Breed;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import com.app.furryguard.entity.dto.*;
import com.app.furryguard.entity.dto.petDtos.AgeDto;
import com.app.furryguard.entity.dto.petDtos.GetAllPetsWithParticularWalkingStatusDto;
import com.app.furryguard.entity.dto.petDtos.GetPetDto;
import com.app.furryguard.entity.dto.petDtos.PetCreateDto;
import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.enums.PetWalkingStatus;
import com.app.furryguard.enums.VaccinationType;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.mapper.FileMapper;
import com.app.furryguard.mapper.PetMapper;
import com.app.furryguard.repository.BreedRepository;
import com.app.furryguard.repository.FileRepository;
import com.app.furryguard.repository.PetRepository;
import com.app.furryguard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final BreedRepository breedRepository;
    private final PetMapper petMapper;
    private final RecommendationService recommendationService;
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Transactional
    public Pet createPet(PetCreateDto petCreateDto) {
        User user = userRepository.findUserByEmail(JwtTokenProvider.getCurrentUserEmail())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        Breed breed = breedRepository.findBreedByNameIgnoreCase(petCreateDto.getBreed())
                .orElseThrow(() -> new InvalidCredentialsException("Breed not found"));

        Pet pet = Pet.builder()
                .name(petCreateDto.getName())
                .weight(petCreateDto.getWeight())
                .gender(petCreateDto.getGender())
                .age(petCreateDto.getAge())
                .activityLevel(petCreateDto.getActivityLevel().name())
                .recommendations(generateRecommendations(petCreateDto))
                .hasRecommendations(getRandomBoolean())
                .petWalkingStatus(PetWalkingStatus.WANT_HOME)
                .breedId(breed)
                .ownerId(user)
                .build();

        System.out.println(recommendationService.getWeightDifference(pet));

        return petRepository.save(pet);
    }

    public GetPetDto getPet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new InvalidCredentialsException("Pet not found"));

        boolean hasRecommendations = getRandomBoolean();

        return GetPetDto.builder()
                .name(pet.getName())
                .gender(pet.getGender())
                .age(pet.getAge())
                .breed(pet.getBreedId().getName())
                .weight(pet.getWeight())
                .activityLevel(ActivityLevel.valueOf(pet.getActivityLevel()))
                .recommendations(hasRecommendations ? pet.getRecommendations() : "У вашего питомца всё в порядке. Рекомендации не требуются")
                .petWalkingStatus(pet.getPetWalkingStatus())
                .vaccinations(generateVaccinationRecommendations())
                .hasRecommendations(hasRecommendations)
//                .files(fileRepository.getAllFilesByPetId(pet).stream()
//                        .map(fileMapper::mapToGetFileDto)
//                        .toList())
                .build();
    }

    public List<String> searchBreedByPattern(String pattern){
        if (pattern == null)
            pattern = "";
        return breedRepository.findByNameContainingIgnoreCase(pattern).stream()
                .map(Breed::getName)
                .sorted()
                .toList();
    }

    @Transactional
    public WalkingAnswerDto changePetWalkingStatus(ChangePetWalkingStatusDto changePetWalkingStatusDto){
        Pet pet = petRepository.findById(changePetWalkingStatusDto.getPetId())
                .orElseThrow(() -> new InvalidCredentialsException("Pet not found"));

        pet.setPetWalkingStatus(changePetWalkingStatusDto.getPetWalkingStatus());
        petRepository.save(pet);

        return WalkingAnswerDto.builder().message("Статус успешно изменен").build();
    }

    public List<GetAllPetsWithParticularWalkingStatusDto> findPetsWithParticularWalkingStatus(WalkingStatusDto walkingStatusDto){

        var petList = petRepository.findAllByPetWalkingStatus(walkingStatusDto.getPetWalkingStatus());

        return petList.stream()
                .map(petMapper::mapToGetAllPetsWithParticularWalkingStatusDto)
                .toList();
    }


    private String generateRecommendations(PetCreateDto petCreateDto){

        return String.format("У вашего питомца повышенный риск ожирения. " +
                "Рекомендуется снизить вес до %d кг. " +
                "Длительность ежедневных прогулок увеличить до %d минут в день. " +
                "Кормить питомца необходимо по %d г. гипоаллергенного корма 3 раза в день.",
                10, 90, 70);
    }

    private String generateVaccinationRecommendations(){

        String vaccinations_descriptions = String.format("\nНаименования вакцин:\n " +
                "1. %s (%s)\n" +
                "2. %s (%s)\n" +
                "3. %s (%s)\n" +
                "4. %s (%s)\n" +
                "5. %s (%s)\n" +
                "6. %s (%s)\n",
                VaccinationType.D, VaccinationType.D.getDescription(),
                VaccinationType.H, VaccinationType.H.getDescription(),
                VaccinationType.P, VaccinationType.P.getDescription(),
                VaccinationType.Pi, VaccinationType.Pi.getDescription(),
                VaccinationType.L, VaccinationType.L.getDescription(),
                VaccinationType.R, VaccinationType.R.getDescription());

        return vaccinations_descriptions + "Рекомендации по вакцинации:\n" +
                "Подготовка собаки:\n" +
                "\n" +
                "За 2 недели до прививки нужно провести противопаразитарную обработку, в том числе дегельминтизацию.\n" +
                "В течение 7 дней до вакцинации ежедневно измерять температуру тела собаки.\n" +
                "Исключить на несколько дней до процедуры контакты с другими животными.";
    }

    private Integer convertAgeToWeeksFloor(AgeDto ageDto){
        return (int) Math.floor(ageDto.getYear()*52.18+ageDto.getWeek()*4.348125+ageDto.getWeek());
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

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
