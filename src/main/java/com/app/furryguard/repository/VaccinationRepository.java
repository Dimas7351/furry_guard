package com.app.furryguard.repository;

import com.app.furryguard.entity.Metro;
import com.app.furryguard.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @NativeQuery("Select * from vaccinations where start_weeks <= ?1 and end_weeks >= ?1")
    List<Vaccination> getVaccinationByWeeksPeriod(int weeksAge);
}

