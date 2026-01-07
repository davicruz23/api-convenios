package com.projetos.convenios.security;

import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.repository.PartnerCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PartnerCompanyRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        PartnerCompany company = repository
                .findByEmail(username);

        return User.builder()
                .username(company.getEmail())
                .password(company.getPassword())
                .roles("PARTNER")
                .build();
    }
}
