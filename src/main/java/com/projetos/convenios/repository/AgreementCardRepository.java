package com.projetos.convenios.repository;

import com.projetos.convenios.domain.AgreementCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementCardRepository extends JpaRepository<AgreementCard,Long> {
}
