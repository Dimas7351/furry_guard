package com.app.furryguard.service;

import com.app.furryguard.entity.Breed;
import com.app.furryguard.entity.BreedWeight;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.Vaccination;
import com.app.furryguard.dto.petDtos.GetRiskDto;
import com.app.furryguard.enums.VaccinationType;
import com.app.furryguard.repository.BreedRepository;
import com.app.furryguard.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final VaccinationRepository vaccinationRepository;
    private final BreedRepository breedRepository;

    public static final double WEIGHT_COEF = 0.36;
    public static final double BREED_COEF = 0.13;
    public static final double FEED_COEF = 0.22;
    public static final double ACTIVITY_COEF = 0.21;
    public static final double AGE_COEF = 0.08;


    public GetRiskDto getRiskCoef(Pet pet){

        double weightRisk = getWeightRisk(pet);
        double breedRisk = getBreedRisk(pet);
        double feedRisk = getFeedRisk(pet);
        double activityRisk = getActivityRisk(pet);
        double ageRisk = getAgeRisk(pet);

        double integralRiskCoef = weightRisk * WEIGHT_COEF
                + breedRisk * BREED_COEF
                + feedRisk * FEED_COEF
                + activityRisk * ACTIVITY_COEF
                + ageRisk * AGE_COEF;

        return GetRiskDto.builder()
                .weightRisk(weightRisk * WEIGHT_COEF)
                .breedRisk(breedRisk * BREED_COEF)
                .feedRisk(feedRisk * FEED_COEF)
                .activityRisk(activityRisk * ACTIVITY_COEF)
                .ageRisk(ageRisk * AGE_COEF)
                .integralRisk(integralRiskCoef)
                .build();
    }

    public double getWeightRisk(Pet pet){

        var ageMonth = pet.getAge().getMonth();
        var weight = pet.getWeight();

        BreedWeight necessaryBreedWeight = pet.getBreedId().getBreedWeights().stream()
                .filter(ageWeight -> ageMonth >= ageWeight.getMinAgeMonths() && ageMonth <= ageWeight.getMaxAgeMonths())
                .findFirst()
                .orElse(null);

        int max_weight = necessaryBreedWeight.getMaxWeightKg();
        double difference = 0;

        if (weight > max_weight){
            if (weight - max_weight > max_weight/3.0)
                difference = 1.0;
            else
                difference = (weight - max_weight)/(max_weight/3.0);
        }

        return difference;
    }

    public double getBreedRisk(Pet pet){
        Breed breed = breedRepository.findById(pet.getBreedId().getId()).orElse(null);

        if (breed == null)
            return 0.3;
        else
            return breed.getOverweightRisk()==null?0:breed.getOverweightRisk();
    }
    public double getFeedRisk(Pet pet){
        int petAge = pet.getAge().getYear()*12 + pet.getAge().getMonth();
        BreedWeight necessaryBreedWeight = pet.getBreedId().getBreedWeights().stream()
                .filter(ageWeight -> petAge >= ageWeight.getMinAgeMonths() && petAge <= ageWeight.getMaxAgeMonths())
                .findFirst()
                .orElse(null);

        double feedNorm = 30 * necessaryBreedWeight.getMaxWeightKg() + 70;
        if (feedNorm >= pet.getFeed())
            return 0;
        else if (pet.getFeed() - feedNorm > feedNorm/2.0)
            return 1.0;
        else
            return (pet.getFeed() - feedNorm)/(feedNorm/2.0);
    }

    public double getActivityRisk(Pet pet){ return 0.3; }
    public double getAgeRisk(Pet pet){
        return 0.2; }

    public List<VaccinationType> getVaccinationRecommendations(Pet pet){
        var ageWeeks = pet.getAge().getWeek();

        List<Vaccination> nowVaccinations = vaccinationRepository.getVaccinationByWeeksPeriod(ageWeeks);
        System.out.println(nowVaccinations);

        return nowVaccinations.stream()
                .map(Vaccination::getVaccinationType)
                .toList();
    }

    // Суточная потребность Калории в Состоянии Покоя
//    private double getKsp(Pet pet){
//        double baseKSP = 30 * pet.getWeight() + 70;
//
//        baseKSP = pet.getSterilized() ? baseKSP * 1.6 : baseKSP * 1.8;
//
//        if (pet.getBreedId().getAvgActivity().equals(ActivityLevel.HIGH))
//            baseKSP *= 3;
//
//        if (overweightRisk)
//            baseKSP *= 1.3;
//
//        if (months < 4)
//            baseKSP *= 3;
//
//        if (months >=4 && months <6)
//            baseKSP *= 2;
//
//        if (months >=6 && months <=8)
//            baseKSP *= 1.2;
//
//        if (berem)
//            baseKSP *= 1.2;
//
//        if (korm_dog)
//            baseKSP *= 2.25;
//
//        return baseKSP;
//    }
//
//
//
//    // частота кормления
//    1 - 6 месяцев = 4-6 раз в день
//    ближе к 6 месяцам = 4 раза в день
//
//    6 - 12 месяцев = 2-3 раза в день
//
//
//    // мелкие и карликовые породы
//    3 раза в день
//    // остальные
//    можно 2 раза в день
//
//
//    public void getAverageFeedNorm(Pet pet){
//        double weight = pet.getWeight();
//        double age =
//
//
//    }
//
//    // щенок (1.5-4 месяца) = 81 + (кг-1)*25
//    // щенок (4-6 месяцев) = 54 + (кг-1)*16
//    // щенок (6-8 месяцев) = 32 + (кг-1)*10
//
//    // мелкие породы (от 2 кг) = 31 + кг*7   420ккал/100г
//    // средние породы (от 12 кг) = 134 + кг*9.5   320ккал/100г
//    // крупные породы (от 25 кг) = 228 + кг*8.25  360ккал/100г
//
//

}
