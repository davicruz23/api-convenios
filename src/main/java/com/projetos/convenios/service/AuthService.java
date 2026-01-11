package com.projetos.convenios.service;

import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.auth.LoginRequestDTO;
import com.projetos.convenios.domain.dto.auth.LoginResponseDTO;
import com.projetos.convenios.repository.PartnerCompanyRepository;
import com.projetos.convenios.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${admin.email}")
    private String adminEmail;

    public LoginResponseDTO login(LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        if (adminEmail.equalsIgnoreCase(dto.getEmail())) {
            String token = jwtService.generateAdminToken(dto.getEmail());
            return new LoginResponseDTO(token);
        }

        PartnerCompany company = repository.findByEmail(dto.getEmail());

        if (company == null) {
            throw new RuntimeException("Company not found");
        }

        String token = jwtService.generateCompanyToken(company);
        return new LoginResponseDTO(token);
    }
}
