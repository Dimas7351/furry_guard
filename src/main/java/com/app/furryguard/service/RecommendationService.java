package com.app.furryguard.service;

import com.app.furryguard.entity.Breed;
import com.app.furryguard.entity.BreedWeight;
import com.app.furryguard.entity.File;
import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.dto.fileDtos.AddFileDto;
import com.app.furryguard.enums.ActivityLevel;
import com.app.furryguard.exceptions.InvalidCredentialsException;
import com.app.furryguard.mapper.FileMapper;
import com.app.furryguard.repository.BreedRepository;
import com.app.furryguard.repository.FileRepository;
import com.app.furryguard.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final BreedRepository breedRepository;

    public static final double WEIGHT_COEF = 0.3;

//    public Pair<Double, String> getIntegralRisk(Pet pet){
//        return new Pair(getWeightDifference(pet)*WEIGHT_COEF, "СоСи");
//    }

    public double getWeightDifference(Pet pet){

        var ageMonth = pet.getAge().getMonth();
        var weight = pet.getWeight();

        BreedWeight necessaryBreedWeight = pet.getBreedId().getBreedWeights().stream()
                .filter(ageWeight -> ageMonth >= ageWeight.getMinAgeMonths() && ageMonth <= ageWeight.getMaxAgeMonths())
                .findFirst()
                .orElse(null);


        int min_weight = necessaryBreedWeight.getMinWeightKg();
        int max_weight = necessaryBreedWeight.getMaxWeightKg();
        double difference = 0;

        if (weight > max_weight)
            difference = (weight - max_weight)/max_weight;
        if (weight < min_weight)
            difference = -1*(min_weight-weight)/min_weight;

        return difference;
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
