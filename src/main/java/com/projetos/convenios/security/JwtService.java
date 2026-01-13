package com.projetos.convenios.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.enums.UserRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "api-convenios";


    public String generateCompanyToken(PartnerCompany company) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(company.getEmail())
                    .withClaim("companyId", company.getId())
                    .withClaim("companyName", company.getName())
                    .withClaim("role", "ROLE_" + UserRoles.fromValue(company.getRoleId()))
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating company token", ex);
        }
    }

    public String generateAdminToken(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(email)
                    .withClaim("role", "ROLE_ADMIN")
                    .withClaim("nome", "Administrador")
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating admin token", ex);
        }
    }


    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    public String extractRole(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getClaim("role")
                .asString();
    }

    private Instant genExpirationDate() {
        return LocalDateTime
                .now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
