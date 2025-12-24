package com.projetos.convenios.service;

import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.dto.partner.HolderWithPartnersDTO;
import com.projetos.convenios.domain.dto.partner.PartnerRequestDTO;
import com.projetos.convenios.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository repository;

    public List<Partner> findAll() {
        return repository.findAll();
    }

    public Partner findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Partner> findByHolder(Partner holder) {
        return repository.findByHolder(holder);
    }

    public Partner createHolder(PartnerRequestDTO dto) {

        Partner holder = new Partner();
        holder.setName(dto.getName());
        holder.setCpf(dto.getCpf());
        holder.setPhone(dto.getPhone());
        holder.setIsHolder(true);
        holder.setHolder(null);

        return repository.save(holder);
    }

    public Partner createDependent(Long holderId, PartnerRequestDTO dto) {

        Partner holder = repository.findById(holderId)
                .orElseThrow(() -> new RuntimeException("Holder not found"));

        if (!Boolean.TRUE.equals(holder.getIsHolder())) {
            throw new RuntimeException("Partner is not a holder");
        }

        long dependentsCount = repository.countByHolderId(holderId);

        if (dependentsCount >= 4) {
            throw new RuntimeException("Titular com máximo de dependentes (4)");
        }

        Partner dependent = new Partner();
        dependent.setName(dto.getName());
        dependent.setCpf(dto.getCpf());
        dependent.setPhone(dto.getPhone());
        dependent.setIsHolder(false);
        dependent.setHolder(holder);

        return repository.save(dependent);
    }

    public HolderWithPartnersDTO findHolderWithDependents(Long holderId) {

        Partner holder = repository.findById(holderId)
                .orElseThrow(() -> new RuntimeException("Holder not found"));

        if (!Boolean.TRUE.equals(holder.getIsHolder())) {
            throw new RuntimeException("Partner is not a holder");
        }

        List<Partner> dependents = repository.findByHolderId(holderId);

        List<String> dependentNames = dependents.stream()
                .map(Partner::getName)
                .toList();

        return HolderWithPartnersDTO.builder()
                .holderId(holderId)
                .phoneHolder(holder.getPhone())
                .holderName(holder.getName())
                .dependents(dependentNames)
                .build();
    }


    public List<Partner> findDependents(Long holderId) {
        return repository.findByHolderId(holderId);
    }
}
