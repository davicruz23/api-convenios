package com.projetos.convenios.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgreementCardResponseDTO {

    private Long id;
    private String cardNumber;
    private String partnerName;
    private String holderName;
    private boolean holder;
    private LocalDate expirationDate;
    private boolean active;
    private String qrCodeHash;
}
