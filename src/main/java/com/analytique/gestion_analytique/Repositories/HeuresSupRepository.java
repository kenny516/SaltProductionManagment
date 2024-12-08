package com.analytique.gestion_analytique.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.HeuresSup;

public interface HeuresSupRepository extends JpaRepository<HeuresSup, Long> {
    @Query(value="SELECT * FROM HeuresSup h " +
       "WHERE h.id_employe = :idEmploye " +
       "AND EXTRACT(MONTH FROM h.date_debut) = :mois " +
       "AND EXTRACT(YEAR FROM h.date_debut) = :annee", nativeQuery=true)
    List<HeuresSup> findByEmployeAndMonthAndYear(@Param("idEmploye") Long idEmploye, @Param("mois") int mois, @Param("annee") int annee);

    @Query("SELECT h FROM HeuresSup h WHERE h.dateDebut >= :today")
    List<HeuresSup> findHeuresSupAfterToday(LocalDateTime today);

    @Query(value="SELECT SUM(hs.montant) FROM HeuresSup hs WHERE hs.employe.id = :employeId " +
           "AND EXTRACT(MONTH FROM hs.date_debut) = :mois " +
           "AND EXTRACT(YEAR FROM hs.date_debut) = :annee " +
           "AND hs.majoration = :majoration", nativeQuery = true)
    Double findTotalMontantByEmployeAndDateAndMajoration(@Param("employeId")Integer employeId,@Param("mois") Integer mois, @Param("annee")Integer annee, Integer majoration);
}
 