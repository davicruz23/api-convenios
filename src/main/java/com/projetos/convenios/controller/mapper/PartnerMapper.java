package com.projetos.convenios.controller.mapper;

import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.dto.partner.PartnerResponseDTO;

public class PartnerMapper {
    public static PartnerResponseDTO mapper (Partner src) {
        return PartnerResponseDTO.builder()
                .name(src.getName())
                .cpf(src.getCpf())
                .phone(src.getPhone())
                .isHolder(src.getIsHolder())
                .build();
    }
}
