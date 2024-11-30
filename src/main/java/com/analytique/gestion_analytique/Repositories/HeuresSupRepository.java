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
}
