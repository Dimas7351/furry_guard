package com.app.furryguard.repository;

import com.app.furryguard.entity.Pet;
import com.app.furryguard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
