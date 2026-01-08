package com.projetos.convenios.service;

import com.projetos.convenios.domain.Address;
import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.dto.partner.HolderWithPartnersDTO;
import com.projetos.convenios.domain.dto.partner.PartnerRequestDTO;
import com.projetos.convenios.domain.dto.partner.PartnerSearchResponseDTO;
import com.projetos.convenios.repository.PartnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        Address address = new Address();
        address.setStreet(dto.getAddress().getStreet());
        address.setHouseNumber(dto.getAddress().getHouseNumber());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setZip(dto.getAddress().getZip());
        address.setCountry(dto.getAddress().getCountry());

        Partner holder = new Partner();
        holder.setName(dto.getName());
        holder.setCpf(dto.getCpf());
        holder.setPhone(dto.getPhone());
        holder.setIsHolder(true);
        holder.setHolder(null);
        holder.setAddress(address);

        return repository.save(holder);
    }

    @Transactional
    public void createDependent(Long holderId, PartnerRequestDTO dto) {

        Partner holder = repository.findById(holderId)
                .orElseThrow(() -> new RuntimeException("Holder not found"));

        if (!Boolean.TRUE.equals(holder.getIsHolder())) {
            throw new RuntimeException("Partner is not a holder");
        }

        long count = repository.countByHolderId(holderId);
        if (count >= 4) {
            throw new RuntimeException("Titular pode ter no máximo 4 dependentes");
        }

        Address address = null;
        if (dto.getAddress() != null) {
            address = new Address();
            address.setStreet(dto.getAddress().getStreet());
            address.setHouseNumber(dto.getAddress().getHouseNumber());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setZip(dto.getAddress().getZip());
            address.setCountry(dto.getAddress().getCountry());
        }

        Partner dependent = new Partner();
        dependent.setName(dto.getName());
        dependent.setCpf(dto.getCpf());
        dependent.setPhone(dto.getPhone());
        dependent.setIsHolder(false);
        dependent.setHolder(holder);
        dependent.setAddress(address);

        repository.save(dependent);
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

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Partner> findByIsHolderTrue() {
        return repository.findByIsHolderTrue();
    }

    public List<PartnerSearchResponseDTO> searchAutocomplete(String query) {

        Pageable pageable = PageRequest.of(0, 10);
        LocalDate today = LocalDate.now();

        return repository.searchAutocomplete(query, pageable)
                .stream()
                .map(dto -> {

                    boolean valido =
                            dto.getExpirationDate() != null &&
                                    !dto.getExpirationDate().isBefore(today);

                    return new PartnerSearchResponseDTO(
                            dto.getId(),
                            dto.getName(),
                            dto.getCpf(),
                            dto.getIsHolder(),
                            dto.getExpirationDate(),
                            valido ? "VALIDO" : "EXPIRADO"
                    );
                })
                .toList();
    }

}
