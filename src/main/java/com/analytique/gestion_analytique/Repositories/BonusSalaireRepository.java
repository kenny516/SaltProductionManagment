package com.analytique.gestion_analytique.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.BonusSalaire;

public interface BonusSalaireRepository extends JpaRepository<BonusSalaire, Integer>{
    @Query(value = "SELECT * FROM bonusSalaire WHERE EXTRACT(YEAR FROM date_bonus)= :annee and EXTRACT(MONTH FROM date_bonus)= :mois and id_employe = :idEmploye type_bonus = (SELECT id FROM type_bonus WHERE nom='INDEMNITE')", nativeQuery = true)
    List<BonusSalaire> getIndemniteByMonthAndYear(@Param("mois")int mois, @Param("annee")int annee, @Param("idEmploye")int idEmploye);

    @Query(value = "SELECT * FROM bonusSalaire WHERE EXTRACT(YEAR FROM date_bonus)= :annee and EXTRACT(MONTH FROM date_bonus)= :mois and id_employe = :idEmploye type_bonus = (SELECT id FROM type_bonus WHERE nom='PRIME')", nativeQuery = true)
    List<BonusSalaire> getPrimeByMonthAndYear(@Param("mois")int mois, @Param("annee")int annee, @Param("idEmploye")int idEmploye);
}
