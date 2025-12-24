package com.projetos.convenios.repository;

import com.projetos.convenios.domain.Partner;
import com.projetos.convenios.domain.dto.partner.HolderWithPartnersDTO;
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

}
