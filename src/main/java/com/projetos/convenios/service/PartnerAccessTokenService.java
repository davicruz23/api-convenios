package com.projetos.convenios.service;

import com.projetos.convenios.domain.PartnerAccessToken;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.repository.PartnerAccessTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerAccessTokenService {

    private final PartnerAccessTokenRepository repository;

    public PartnerAccessToken createToken(PartnerCompany company) {
        PartnerAccessToken token = new PartnerAccessToken();
        token.setCompany(company);
        return repository.save(token);
    }
}