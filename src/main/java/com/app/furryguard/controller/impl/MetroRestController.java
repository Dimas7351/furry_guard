package com.app.furryguard.controller.impl;

import com.app.furryguard.controller.MetroResource;
import com.app.furryguard.service.MetroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MetroRestController implements MetroResource {

    private final MetroService metroService;

    @Override
    public List<String> searchMetroByPattern(String pattern) {
        return metroService.searchMetroByPattern(pattern);
    }
}
