package com.projetos.convenios.controller;

import com.projetos.convenios.controller.mapper.PartnerCompanyMapper;
import com.projetos.convenios.controller.mapper.PartnerMapper;
import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.dto.partner.PartnerRequestDTO;
import com.projetos.convenios.domain.dto.partner.PartnerResponseDTO;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyRequestDTO;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyResponseDTO;
import com.projetos.convenios.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService service;

    @GetMapping
    public ResponseEntity<List<PartnerResponseDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll()
                        .stream()
                        .map(PartnerMapper::mapper)
                        .toList());
    }

    @PostMapping("/createHolder")
    public ResponseEntity<PartnerRequestDTO> createHolder(@RequestBody PartnerRequestDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(service.createHolder(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{holderId}/createDependent")
    public ResponseEntity<PartnerRequestDTO> createDependent(@PathVariable Long holderId, @RequestBody PartnerRequestDTO dto) {
        Partner created = service.createDependent(holderId, dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
