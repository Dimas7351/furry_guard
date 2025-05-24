package com.app.furryguard.repository;

import com.app.furryguard.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {

    Optional<Breed> findBreedByNameIgnoreCase(String name);

    List<Breed> findByNameContainingIgnoreCase(String pattern);

}
