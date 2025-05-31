package com.app.furryguard.repository;

import com.app.furryguard.entity.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetroRepository extends JpaRepository<Metro, Long> {

    List<Metro> findByNameContainingIgnoreCase(String pattern);

}

