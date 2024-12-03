package com.analytique.gestion_analytique.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.BonusSalaire;

public interface BonusSalaireRepository extends JpaRepository<BonusSalaire, Integer>{
    @Query(value = "SELECT * FROM bonus_Salaire WHERE EXTRACT(YEAR FROM date_bonus)= :annee and EXTRACT(MONTH FROM date_bonus)= :mois and id_employe = :idEmploye and type_bonus = (SELECT id FROM type_bonus_salaire WHERE nom='INDEMNITE')", nativeQuery = true)
    List<BonusSalaire> getIndemniteByMonthAndYear(@Param("mois")int mois, @Param("annee")int annee, @Param("idEmploye")int idEmploye);

    @Query(value = "SELECT * FROM bonus_Salaire WHERE EXTRACT(YEAR FROM date_bonus)= :annee and EXTRACT(MONTH FROM date_bonus)= :mois and id_employe = :idEmploye and type_bonus = (SELECT id FROM type_bonus_salaire WHERE nom='PRIME')", nativeQuery = true)
    List<BonusSalaire> getPrimeByMonthAndYear(@Param("mois")int mois, @Param("annee")int annee, @Param("idEmploye")int idEmploye);
}
