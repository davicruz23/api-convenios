package com.projetos.convenios.repository;

import com.projetos.convenios.domain.PartnerAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerAccessTokenRepository extends JpaRepository<PartnerAccessToken, Long> {

    Optional<PartnerAccessToken> findByToken(String token);
}
