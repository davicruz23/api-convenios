package com.projetos.convenios.controller;

import com.projetos.convenios.controller.mapper.PartnerCompanyMapper;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyRequestDTO;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyResponseDTO;
import com.projetos.convenios.service.PartnerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/partner-companies")
@RequiredArgsConstructor
public class PartnerCompanyController {

    private final PartnerCompanyService service;

    @GetMapping
    public ResponseEntity<List<PartnerCompanyResponseDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll()
                        .stream()
                        .map(PartnerCompanyMapper::mapper)
                        .toList());
    }

    @PostMapping
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
}
