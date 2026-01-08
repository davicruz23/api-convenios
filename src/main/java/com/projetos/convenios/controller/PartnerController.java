package com.projetos.convenios.controller;

import com.projetos.convenios.controller.mapper.PartnerCompanyMapper;
import com.projetos.convenios.controller.mapper.PartnerMapper;
import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.dto.partner.HolderWithPartnersDTO;
import com.projetos.convenios.domain.dto.partner.PartnerRequestDTO;
import com.projetos.convenios.domain.dto.partner.PartnerResponseDTO;
import com.projetos.convenios.domain.dto.partner.PartnerSearchResponseDTO;
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

    @GetMapping("/getHolders")
    public ResponseEntity<List<PartnerResponseDTO>> findAllHolders() {
        return ResponseEntity.ok()
                .body(service.findByIsHolderTrue()
                        .stream()
                        .map(PartnerMapper::mapper)
                        .toList());
    }

    @GetMapping("/{holderId}/grouped-dependents")
    public ResponseEntity<HolderWithPartnersDTO> findGroupedDependents(@PathVariable Long holderId) {
        return ResponseEntity.ok(service.findHolderWithDependents(holderId));
    }

    @PostMapping("/createHolder")
    public ResponseEntity<Long> createHolder(@RequestBody PartnerRequestDTO dto) {
        Partner holder = service.createHolder(dto);
        return ResponseEntity.ok(holder.getId());
    }

    @PostMapping("/{holderId}/createDependent")
    public ResponseEntity<Void> createDependent(
            @PathVariable Long holderId,
            @RequestBody PartnerRequestDTO dto
    ) {
        service.createDependent(holderId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<PartnerSearchResponseDTO> search(
            @RequestParam(required = false, defaultValue = "") String q
    ) {
        return service.searchAutocomplete(q);
    }

}
