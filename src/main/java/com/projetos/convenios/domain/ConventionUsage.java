package com.projetos.convenios.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConventionUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime usedAt;
    private Integer discountApplied;
    private String procedureName;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Partner partner;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private PartnerCompany partnerCompany;

}

