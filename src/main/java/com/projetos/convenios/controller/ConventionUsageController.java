package com.projetos.convenios.controller;

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

    @PostMapping("/apply")
    public ResponseEntity<Integer> applyDiscount(@RequestParam Long partnerId, @RequestParam Long companyId) {

        int discount = service.applyDiscount(partnerId, companyId);
        return ResponseEntity.ok(discount);
    }
}

