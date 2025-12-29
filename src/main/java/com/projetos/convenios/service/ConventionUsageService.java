package com.projetos.convenios.service;

import com.projetos.convenios.domain.ConventionUsage;
import com.projetos.convenios.domain.DiscountCalculator;
import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.PartnerCompany;
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

    @Transactional
    public int applyDiscount(Long partnerId, Long companyId) {

        if (!cardService.isValid(partnerId)) {
            throw new RuntimeException("Invalid agreement card");
        }

        Partner partner = partnerService.findById(partnerId);

        PartnerCompany company = partnerCompanyService.findById(companyId);

        int discount = DiscountCalculator.calculate(company.getDiscountMax());

        ConventionUsage usage = new ConventionUsage();
        usage.setPartner(partner);
        usage.setPartnerCompany(company);
        usage.setUsedAt(LocalDateTime.now());
        usage.setDiscountApplied(discount);

        repository.save(usage);

        return discount;
    }
}

