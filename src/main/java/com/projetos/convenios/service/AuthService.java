package com.projetos.convenios.service;

import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.auth.LoginRequestDTO;
import com.projetos.convenios.domain.dto.auth.LoginResponseDTO;
import com.projetos.convenios.repository.PartnerCompanyRepository;
import com.projetos.convenios.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PartnerCompanyRepository repository;

    public LoginResponseDTO login(LoginRequestDTO dto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getEmail(),
                                dto.getPassword()
                        )
                );

        PartnerCompany company = repository
                .findByEmail(dto.getEmail());

        Map<String, Object> claims = new HashMap<>();
        claims.put("companyId", company.getId());
        claims.put("companyName", company.getName());

        String token = jwtService.generateToken(claims, company.getEmail());

        return new LoginResponseDTO(
                token,
                company.getId(),
                company.getName()
        );
    }
}
