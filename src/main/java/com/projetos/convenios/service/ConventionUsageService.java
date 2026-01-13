package com.projetos.convenios.service;

import com.projetos.convenios.domain.ConventionUsage;
import com.projetos.convenios.domain.DiscountCalculator;
import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.conventionUsage.ConventionUsageDTO;
import com.projetos.convenios.repository.ConventionUsageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConventionUsageService {

    private final AgreementCardService cardService;
    private final PartnerService partnerService;
    private final PartnerCompanyService partnerCompanyService;
    private final ConventionUsageRepository repository;

    public int calculateDiscount(ConventionUsageDTO dto) {

        if (!cardService.isValid(dto.getPartnerId())) {
            throw new RuntimeException("Invalid agreement card");
        }

        PartnerCompany company = partnerCompanyService.findById(dto.getCompanyId());

        return DiscountCalculator.calculate(company.getDiscountMax());
    }

    @Transactional
    public void confirmDiscount(ConventionUsageDTO dto) {

        Partner partner = partnerService.findById(dto.getPartnerId());
        PartnerCompany company = partnerCompanyService.findById(dto.getCompanyId());

        ConventionUsage usage = new ConventionUsage();
        usage.setPartner(partner);
        usage.setPartnerCompany(company);
        usage.setUsedAt(LocalDateTime.now());
        usage.setProcedureName(dto.getProcedureName());
        usage.setDiscountApplied(dto.getDiscount());

        repository.save(usage);
    }
}


