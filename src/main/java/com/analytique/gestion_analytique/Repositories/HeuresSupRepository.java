package com.analytique.gestion_analytique.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.HeuresSup;

public interface HeuresSupRepository extends JpaRepository<HeuresSup, Long> {
    @Query("SELECT h FROM HeuresSup h WHERE h.idEmploye = :idEmploye AND FUNCTION('DATE_TRUNC', 'week', h.dateDebut) = :semaine")
    List<HeuresSup> findByEmployeAndWeek(@Param("idEmploye") Long idEmploye, @Param("semaine") LocalDate semaine);

    @Query(value="SELECT * FROM HeuresSup h " +
       "WHERE h.id_employe = :idEmploye " +
       "AND EXTRACT(MONTH FROM h.date_debut) = :mois " +
       "AND EXTRACT(YEAR FROM h.date_debut) = :annee", nativeQuery=true)
    List<HeuresSup> findByEmployeAndMonthAndYear(@Param("idEmploye") Long idEmploye, @Param("mois") int mois, @Param("annee") int annee);

}
