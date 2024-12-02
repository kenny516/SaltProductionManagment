package com.analytique.gestion_analytique.Repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.dto.VueHeuresSupSemaineDTO;

public interface HeuresSupRepository extends JpaRepository<HeuresSup, Long> {

    @Query(value = """
        SELECT new com.analytique.gestion_analytique.dto.VueHeuresSupSemaineDTO(        
            h.employe.id,
            DATE_TRUNC('week', h.dateDebut),
            SUM(h.totalHeuresSup),
            SUM(h.montant)
        )
        FROM HeuresSup h
        WHERE h.employe.id = :idEmploye
        AND DATE_TRUNC('week', h.dateDebut) = :semaine
        GROUP BY h.employe.id, DATE_TRUNC('week', h.dateDebut)
        """)
    VueHeuresSupSemaineDTO findVueHeuresSupByEmployeAndSemaine(
        @Param("idEmploye") Integer idEmploye,
        @Param("semaine") LocalDate semaine
    );    
    

    @Query("SELECT h FROM HeuresSup h WHERE h.dateDebut >= :today")
    List<HeuresSup> findHeuresSupAfterToday(LocalDateTime today);

}
