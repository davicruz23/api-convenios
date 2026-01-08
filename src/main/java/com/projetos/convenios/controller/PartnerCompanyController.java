package com.projetos.convenios.controller;

import com.projetos.convenios.controller.mapper.PartnerCompanyMapper;
import com.projetos.convenios.domain.PartnerAccessToken;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyRequestDTO;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyResponseDTO;
import com.projetos.convenios.repository.PartnerAccessTokenRepository;
import com.projetos.convenios.service.PartnerAccessTokenService;
import com.projetos.convenios.service.PartnerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/partner-companies")
@RequiredArgsConstructor
public class PartnerCompanyController {

    private final PartnerCompanyService service;
    private final PartnerAccessTokenRepository tokenRepository;

    @GetMapping
    public ResponseEntity<List<PartnerCompanyResponseDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll()
                        .stream()
                        .map(PartnerCompanyMapper::mapper)
                        .toList());
    }

    @PostMapping("/create-company")
    public ResponseEntity<PartnerCompanyRequestDTO> create(@RequestBody PartnerCompanyRequestDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PartnerCompanyResponseDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/token-validate")
    public ResponseEntity<PartnerCompanyResponseDTO> accessPartner(
            @RequestParam String token
    ) {
        PartnerAccessToken accessToken = tokenRepository
                .findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Token inválido"
                ));

        if (accessToken.getUsed()) {
            throw new ResponseStatusException(
                    HttpStatus.GONE, "Token já utilizado"
            );
        }

        if (accessToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.GONE, "Token expirado"
            );
        }

        // marca como usado
        accessToken.setUsed(true);
        tokenRepository.save(accessToken);

        PartnerCompany company = accessToken.getCompany();

        PartnerCompanyResponseDTO response = PartnerCompanyMapper.mapper(company);

        return ResponseEntity.ok(response);
    }
}
