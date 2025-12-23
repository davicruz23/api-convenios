package com.projetos.convenios.service;

import com.projetos.convenios.domain.Partner;
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

    public List<Partner> findDependents(Long holderId) {
        return repository.findByHolderId(holderId);
    }
}
