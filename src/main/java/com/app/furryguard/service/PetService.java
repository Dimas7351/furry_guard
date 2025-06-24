package com.app.furryguard.service;

import com.app.furryguard.config.JwtTokenProvider;
import com.app.furryguard.dto.ChangePetWalkingStatusDto;
import com.app.furryguard.dto.VaccinationsDto;
import com.app.furryguard.dto.WalkingAnswerDto;
import com.app.furryguard.dto.WalkingStatusDto;
import com.app.furryguard.dto.petDtos.*;
import com.app.furryguard.entity.Breed;
import com.app.furryguard.entity.BreedWeight;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
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
    protected static final String BREED_NOT_FOUND = "Breed not found";
    protected static final String PET_NOT_FOUND = "Pet not found";

    @Transactional
    public Pet createPet(PetCreateDto petCreateDto) {
        User user = userRepository.findUserByEmail(JwtTokenProvider.getCurrentUserEmail())
                .orElseThrow(() -> new InvalidCredentialsException(UserService.USER_NOT_FOUND));

        Breed breed = breedRepository.findBreedByNameIgnoreCase(petCreateDto.getBreed())
                .orElseThrow(() -> new InvalidCredentialsException(BREED_NOT_FOUND));

        Pet pet = Pet.builder()
                .name(petCreateDto.getName())
                .weight(petCreateDto.getWeight())
                .gender(petCreateDto.getGender())
                .age(petCreateDto.getAge())
                .activityLevel(petCreateDto.getActivityLevel().name())
                .petWalkingStatus(PetWalkingStatus.WANT_HOME)
                .breedId(breed)
                .ownerId(user)
                .feed(petCreateDto.getFeed())
                .exactActivity(petCreateDto.getExactActivity())
                .build();

        log.info("risk: {}", recommendationService.getWeightRisk(pet));

        Pet pet2 = petRepository.save(pet);
        GetRiskDto riskDto = recommendationService.getRiskCoef(pet2);
        log.info(String.format("Риск ожирения = %f", riskDto.getIntegralRisk()));
        pet2.setRecommendations(generateRecommendations(pet));
        pet2.setHasRecommendations(riskDto.getIntegralRisk() >= 0.2);

        return petRepository.save(pet2);
    }

    public GetPetDto getPet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new InvalidCredentialsException(PET_NOT_FOUND));

        GetRiskDto riskDto = recommendationService.getRiskCoef(pet);
        log.info(String.format("Риск ожирения = %f", riskDto.getIntegralRisk()));

        return GetPetDto.builder()
                .name(pet.getName())
                .gender(pet.getGender())
                .age(pet.getAge())
                .breed(pet.getBreedId().getName())
                .weight(pet.getWeight())
                .activityLevel(ActivityLevel.valueOf(pet.getActivityLevel()))
                .recommendations(generateRecommendations(pet))
                .petWalkingStatus(pet.getPetWalkingStatus())
                .vaccinations(generateVaccinationRecommendations(pet))
                .hasRecommendations(riskDto.getIntegralRisk() >= 0.2)
                .feed(pet.getFeed())
                .exactActivity(pet.getExactActivity())
                .build();
    }

    public Pet updatePet(Long petId, UpdatePetDto updatePetDto) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new InvalidCredentialsException(PET_NOT_FOUND));

        if (updatePetDto.getAge() != null) {
            pet.setAge(updatePetDto.getAge());
        }
        if (updatePetDto.getWeight() != null) {
            pet.setWeight(updatePetDto.getWeight());
        }
        if (updatePetDto.getDateOfBirth() != null) {
            pet.setDateOfBirth(updatePetDto.getDateOfBirth());
        }
        if (updatePetDto.getFeed() != null) {
            pet.setFeed(updatePetDto.getFeed());
        }
        if (updatePetDto.getExactActivity() != null) {
            pet.setExactActivity(updatePetDto.getExactActivity());
        }

        GetRiskDto riskDto = recommendationService.getRiskCoef(pet);
        log.info(String.format("Риск ожирения = %f", riskDto.getIntegralRisk()));
        pet.setRecommendations(generateRecommendations(pet));
        pet.setHasRecommendations(riskDto.getIntegralRisk() >= 0.2);
        petRepository.save(pet);
        return pet;
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
                .orElseThrow(() -> new InvalidCredentialsException(PET_NOT_FOUND));

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


    private String generateRecommendations(Pet pet){

        Breed breed = breedRepository.findById(pet.getBreedId().getId())
                .orElseThrow(() -> new InvalidCredentialsException(BREED_NOT_FOUND));

        int petAge = pet.getAge().getYear()*12 + pet.getAge().getMonth();

        BreedWeight necessaryBreedWeight = pet.getBreedId().getBreedWeights().stream()
                .filter(ageWeight -> petAge >= ageWeight.getMinAgeMonths() && petAge <= ageWeight.getMaxAgeMonths())
                .findFirst()
                .orElse(null);


        GetRiskDto riskDto = recommendationService.getRiskCoef(pet);
        double integralRisk = riskDto.getIntegralRisk();

        if (integralRisk < 0.2){
            return "У вашего питомца всё в порядке. Ркомендации не требуются";
        } else if (integralRisk >= 0.2 && integralRisk < 0.4){
            String weightRecommedations = "";
            String activityRecommendations = "";
            String feedRecommedations = "";

            int feedNorm = 30 * necessaryBreedWeight.getMaxWeightKg() + 70;

            if (riskDto.getWeightRisk() > 0)
                weightRecommedations = String.format("Рекомендуемый вес - %d кг. ", necessaryBreedWeight.getMaxWeightKg());
            if (riskDto.getActivityRisk() > 0)
                activityRecommendations = String.format("Длительность ежедневных прогулок необходимо увеличить до %d минут в день. ", 130);
            if (riskDto.getFeedRisk() > 0)
                feedRecommedations = String.format("У вашего питомца имеется избыток дневного рациона. Рекомендуется снизить ежедневную дозу корма до %d граммов. ", feedNorm);

            return "У вашего питомца повышенный риск ожирения. " +
                            weightRecommedations +
                            activityRecommendations +
                            feedRecommedations +
                            "При возникновении вопросов проконсультируйтесь с ветеринарным врачом";
        }
        return "У вашего питомца серьезные отклонения! Необходима срочная консультация специалиста!";
    }

    private VaccinationsDto generateVaccinationRecommendations(Pet pet){

        VaccinationsDto vaccinationsDto = new VaccinationsDto();

        String vaccinations_descriptions = String.format("\nНаименования вакцин:\n " +
                "1. %s (%s)\n" +
                "2. %s (%s)\n" +
                "3. %s (%s)\n" +
                "4. %s (%s)\n" +
                "5. %s (%s)\n" +
                "6. %s (%s)\n" +
                "\nПодготовка питомца к вакцинации:\n" +
                "1. За 2 недели до прививки нужно провести противопаразитарную обработку, в том числе дегельминтизацию.\n" +
                "2. В течение 7 дней до вакцинации ежедневно измерять температуру тела собаки.\n" +
                "3. Исключить на несколько дней до процедуры контакты с другими животными.\n",
                VaccinationType.D, VaccinationType.D.getDescription(),
                VaccinationType.H, VaccinationType.H.getDescription(),
                VaccinationType.P, VaccinationType.P.getDescription(),
                VaccinationType.Pi, VaccinationType.Pi.getDescription(),
                VaccinationType.L, VaccinationType.L.getDescription(),
                VaccinationType.R, VaccinationType.R.getDescription());

        vaccinationsDto.setCommonHeader("Общие рекомендации");
        vaccinationsDto.setCommon(vaccinations_descriptions);

        List<VaccinationType> nowVaccinationTypes = recommendationService.getVaccinationRecommendations(pet);
        String nowVaccinations = nowVaccinationTypes.toString();

        vaccinationsDto.setPreviousHeader("Прошедшие вакцины");
        vaccinationsDto.setPrevious(String.format(
                "1. %s (%s) - в возрасте 5-7 недель\n" +
                "2. %s (%s) - в возрасте 5-7 недель\n" +
                "3. %s (%s) - в возрасте 5-7 недель\n",
                VaccinationType.H, VaccinationType.H.getDescription(),
                VaccinationType.P, VaccinationType.P.getDescription(),
                VaccinationType.Pi, VaccinationType.Pi.getDescription()));

        vaccinationsDto.setCurrentHeader("Текущие вакцины");
        vaccinationsDto.setCurrent(String.format(
                        "1. %s (%s)\n" +
                        "2. %s (%s)\n" +
                        "Также необходимо проконсультироваться с ветеринаром о возможности выполнить прошедшие вакцины",
                VaccinationType.Pi, VaccinationType.Pi.getDescription(),
                VaccinationType.L, VaccinationType.L.getDescription()));

        vaccinationsDto.setNextHeader("Предстоящие вакцины");
        vaccinationsDto.setNext(String.format(
                "1. %s (%s) - через 2 месяца",
                VaccinationType.R, VaccinationType.R.getDescription()));

        return vaccinationsDto;
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
