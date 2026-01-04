package com.projetos.convenios.service;

import com.projetos.convenios.domain.Address;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyRequestDTO;
import com.projetos.convenios.repository.PartnerCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerCompanyService {

    private final PartnerCompanyRepository repository;

    public PartnerCompany create(PartnerCompanyRequestDTO dto) {

        Address address = new Address();
        address.setStreet(dto.getAddress().getStreet());
        address.setHouseNumber(dto.getAddress().getHouseNumber());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setZip(dto.getAddress().getZip());
        address.setCountry(dto.getAddress().getCountry());

        PartnerCompany entity = new PartnerCompany();
        entity.setName(dto.getName());
        entity.setCnpj(dto.getCnpj());
        entity.setPhone(dto.getPhone());
        entity.setDiscountMax(dto.getMaxDiscount());
        entity.setAddress(address);

        return repository.save(entity);
    }

    public List<PartnerCompany> findAll() {
        return repository.findAll();
    }

    public  PartnerCompany findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
