package com.projetos.convenios.domain.dto.conventionUsage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConventionUsageDTO {

    private Long partnerId;
    private Long companyId;
    private int discount;
    private String procedureName;
}
