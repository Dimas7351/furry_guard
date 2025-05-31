package com.app.furryguard.service;

import com.app.furryguard.entity.Metro;
import com.app.furryguard.repository.MetroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetroService {

    private final MetroRepository metroRepository;

    public List<String> searchMetroByPattern(String pattern){
        if (pattern == null)
            pattern = "";
        return metroRepository.findByNameContainingIgnoreCase(pattern).stream()
                .map(Metro::getName)
                .toList();
    }

}
