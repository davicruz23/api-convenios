package com.projetos.convenios.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private Boolean isHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id") // nome da coluna no banco
    private Partner holder;
}
