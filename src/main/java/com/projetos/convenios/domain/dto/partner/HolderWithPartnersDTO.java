package com.projetos.convenios.domain.dto.partner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HolderWithPartnersDTO {
    private Long holderId;
    private String phoneHolder;
    private String holderName;
    private List<String> dependents;
}
