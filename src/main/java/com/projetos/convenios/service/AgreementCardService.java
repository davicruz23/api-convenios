package com.projetos.convenios.service;

import com.projetos.convenios.domain.AgreementCard;
import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.repository.AgreementCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementCardService {

    private final AgreementCardRepository repository;
    private final PartnerService partnerService;

    public void createForHolder(Long holderId) {

        Partner holder = partnerService.findById(holderId);

        if (!Boolean.TRUE.equals(holder.getIsHolder())) {
            throw new RuntimeException("Partner is not a holder");
        }

        LocalDate expirationDate = LocalDate.now().plusMonths(1);

        createCardIfNotExists(holder, expirationDate);

        List<Partner> dependents = partnerService.findByHolder(holder);

        for (Partner dependent : dependents) {
            createCardIfNotExists(dependent, expirationDate);
        }
    }

    private void createCardIfNotExists(Partner partner, LocalDate expirationDate) {

        if (repository.existsByPartnerId(partner.getId())) {
            return;
        }

        AgreementCard card = new AgreementCard();
        card.setPartner(partner);
        card.setQrCodeHash(UUID.randomUUID().toString());
        card.setExpirationDate(expirationDate);

        repository.save(card);
    }

    public boolean isValid(Long partnerId) {
        return repository.findByPartnerId(partnerId)
                .map(card -> card.getExpirationDate().isAfter(LocalDate.now()))
                .orElse(false);
    }
}

