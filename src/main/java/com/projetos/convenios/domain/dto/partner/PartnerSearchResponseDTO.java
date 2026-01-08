package com.projetos.convenios.domain.dto.partner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerSearchResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private Boolean isHolder;
    private LocalDate expirationDate;
    private String status;
}