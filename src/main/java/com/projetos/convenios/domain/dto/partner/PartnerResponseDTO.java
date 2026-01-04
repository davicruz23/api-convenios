package com.projetos.convenios.domain.dto.partner;

import com.projetos.convenios.domain.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerResponseDTO {

    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private Boolean isHolder;
    private Long holderId;

}
