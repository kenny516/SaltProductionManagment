package com.analytique.gestion_analytique.Repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.analytique.gestion_analytique.Models.HeuresSup;
import com.analytique.gestion_analytique.dto.VueHeuresSupSemaineDTO;

public interface HeuresSupRepository extends JpaRepository<HeuresSup, Long> {

        @Query(value = """
        SELECT 
            h.id_employe AS idEmploye,
            DATE_TRUNC('week', h.date_debut) AS semaine,
            SUM(h.total_heures_sup) AS totalHeuresSup,
            SUM(h.montant) AS totalMontant
        FROM heuressup h
        WHERE h.id_employe = :idEmploye
          AND DATE_TRUNC('week', h.date_debut) = :semaine
        GROUP BY h.id_employe, DATE_TRUNC('week', h.date_debut)
        """, nativeQuery = true)
    VueHeuresSupSemaineDTO findVueHeuresSupByEmployeAndSemaine(Integer idEmploye, LocalDate semaine);

    @Query("SELECT h FROM HeuresSup h WHERE h.dateDebut >= :today")
    List<HeuresSup> findHeuresSupAfterToday(LocalDateTime today);

}
