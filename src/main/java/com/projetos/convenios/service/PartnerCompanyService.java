package com.projetos.convenios.service;

import com.projetos.convenios.domain.Address;
import com.projetos.convenios.domain.PartnerAccessToken;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyRequestDTO;
import com.projetos.convenios.enums.UserRoles;
import com.projetos.convenios.repository.PartnerAccessTokenRepository;
import com.projetos.convenios.repository.PartnerCompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartnerCompanyService {

    private final PartnerCompanyRepository repository;
    private final PartnerAccessTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PartnerCompany create(PartnerCompanyRequestDTO dto) {

        Address address = new Address();
        address.setStreet(dto.getAddress().getStreet());
        address.setHouseNumber(dto.getAddress().getHouseNumber());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setZip(dto.getAddress().getZip());
        address.setCountry(dto.getAddress().getCountry());

        PartnerCompany company = new PartnerCompany();
        company.setName(dto.getName());
        company.setCnpj(dto.getCnpj());
        company.setPhone(dto.getPhone());
        company.setDiscountMax(dto.getMaxDiscount());
        company.setEmail(dto.getEmail());
        company.setActive(true);
        company.setRoleId(UserRoles.COMPANY.getValue());
        company.setPassword(passwordEncoder.encode(dto.getPassword()));
        company.setAddress(address);

        //        PartnerAccessToken accessToken = new PartnerAccessToken();
//        accessToken.setCompany(savedCompany);
//        tokenRepository.save(accessToken);
//
//        String link = "http://localhost:4200/partner/access?token=" + accessToken.getToken();
//
//        emailService.sendPartnerAccessEmail(dto.getEmail(), link);

        return repository.save(company);
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
