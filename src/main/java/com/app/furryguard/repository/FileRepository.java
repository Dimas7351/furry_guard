package com.app.furryguard.repository;

import com.app.furryguard.entity.File;
import com.app.furryguard.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> getAllFilesByPetId(Pet pet);

}
