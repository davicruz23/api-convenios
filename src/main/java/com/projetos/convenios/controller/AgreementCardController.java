package com.projetos.convenios.controller;

import com.projetos.convenios.domain.AgreementCard;
import com.projetos.convenios.service.AgreementCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/agreement-cards")
@RequiredArgsConstructor
public class AgreementCardController {

    private final AgreementCardService service;

    @PostMapping("/activate-holder/{holderId}")
    public ResponseEntity<Void> activateHolder(@PathVariable Long holderId) {

        service.createForHolder(holderId);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/is-valid/{partnerId}")
    public ResponseEntity<Boolean> isValid(@PathVariable Long partnerId) {
        boolean valid = service.isValid(partnerId);
        return ResponseEntity.ok(valid);
    }
}
