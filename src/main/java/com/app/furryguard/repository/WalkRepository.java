package com.app.furryguard.repository;

import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Long> {

    List<Walk> findWalksByPetId(Pet petId);

}
