package com.projetos.convenios.security;

import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.repository.PartnerCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PartnerCompanyRepository repository;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (adminEmail.equalsIgnoreCase(username)) {
            return User.builder()
                    .username(adminEmail)
                    .password(adminPassword)
                    .roles("ADMIN")
                    .build();
        }

        PartnerCompany company = repository.findByEmail(username);

        if (company == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(company.getEmail())
                .password(company.getPassword())
                .roles("COMPANY")
                .build();
    }
}
