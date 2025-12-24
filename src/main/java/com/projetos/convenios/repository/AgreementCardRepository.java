package com.projetos.convenios.repository;

import com.projetos.convenios.domain.AgreementCard;
import com.projetos.convenios.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementCardRepository extends JpaRepository<AgreementCard,Long> {

    boolean existsByPartnerId(Long partnerId);

    Optional<AgreementCard> findByPartnerId(Long partnerId);

    Optional<AgreementCard> findByPartner(Partner partner);

    Optional<AgreementCard> findByQrCodeHash(String qrCodeHash);
}
