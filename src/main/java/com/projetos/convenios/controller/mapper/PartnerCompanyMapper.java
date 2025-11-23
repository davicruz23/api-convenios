package com.projetos.convenios.controller.mapper;

import com.projetos.convenios.domain.Address;
import com.projetos.convenios.domain.PartnerCompany;
import com.projetos.convenios.domain.dto.address.AddressDTO;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyRequestDTO;
import com.projetos.convenios.domain.dto.partnerCompany.PartnerCompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartnerCompanyMapper {

    public static PartnerCompanyResponseDTO mapper(PartnerCompany src) {
        return PartnerCompanyResponseDTO.builder()
                .id(src.getId())
                .name(src.getName())
                .cnpj(src.getCnpj())
                .phone(src.getPhone())
                .address(AddressMapper.mapper(src.getAddress()))
                .build();
    }
}
