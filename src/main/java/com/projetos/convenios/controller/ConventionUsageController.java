package com.projetos.convenios.controller;

import com.projetos.convenios.domain.dto.conventionUsage.ConventionUsageDTO;
import com.projetos.convenios.service.ConventionUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convention-usage")
@RequiredArgsConstructor
public class ConventionUsageController {

    private final ConventionUsageService service;

    @PostMapping("/calculate")
    public ResponseEntity<Integer> calculate(ConventionUsageDTO dto) {

        int discount = service.calculateDiscount(dto);
        return ResponseEntity.ok(discount);
    }

    @PostMapping("/confirm")
    public ResponseEntity<Void> confirm( ConventionUsageDTO dto) {

        service.confirmDiscount(dto);
        return ResponseEntity.ok().build();
    }
}

