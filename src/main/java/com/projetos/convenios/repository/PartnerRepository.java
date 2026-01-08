package com.projetos.convenios.repository;

import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.dto.partner.HolderWithPartnersDTO;
import com.projetos.convenios.domain.dto.partner.PartnerSearchResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    List<Partner> findByHolderId(Long holderId);

    long countByHolderId(Long holderId);

    @Query("""
    SELECT d
    FROM Partner d
    WHERE d.holder.id = :holderId
""")
    List<Partner> findDependentsByHolderId(@Param("holderId") Long holderId);

    List<Partner> findByHolder(Partner holder);

    List<Partner> findByIsHolderTrue();

    @Query("""
    select new com.projetos.convenios.domain.dto.partner.PartnerSearchResponseDTO(
        p.id,
        p.name,
        p.cpf,
        p.isHolder,
        ac.expirationDate,
        case
            when ac.expirationDate is null then 'SEM_CARTAO'
            when ac.expirationDate >= current_date then 'ATIVO'
            else 'EXPIRADO'
        end
    )
    from Partner p
    left join p.agreementCard ac
    where
        lower(p.name) like lower(concat('%', :query, '%'))
        or p.cpf like concat('%', :query, '%')
    order by p.name
""")
    List<PartnerSearchResponseDTO> searchAutocomplete(
            @Param("query") String query,
            Pageable pageable
    );


}
