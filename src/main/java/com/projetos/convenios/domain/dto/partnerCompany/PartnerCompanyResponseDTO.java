package com.projetos.convenios.domain.dto.partnerCompany;

import com.projetos.convenios.domain.dto.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerCompanyResponseDTO {

    private Long id;
    private String name;
    private String cnpj;
    private String phone;
    private Integer maxDiscount;
    private AddressDTO address;
}
