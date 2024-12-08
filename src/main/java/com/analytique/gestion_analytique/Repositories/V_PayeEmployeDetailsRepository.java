package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.V_PayeEmployeDetails;


public interface V_PayeEmployeDetailsRepository extends JpaRepository<V_PayeEmployeDetails, Integer> {

    @Query(value = "SELECT * FROM V_Paye_Employe_Details " +
                   "WHERE mois = :mois " +
                   "AND annee = :annee " +
                   "AND id = :idEmploye", 
           nativeQuery = true)
    V_PayeEmployeDetails getPayeDetails(@Param("mois") Integer mois, 
                                        @Param("annee") Integer annee, 
                                        @Param("idEmploye") Integer idEmploye);
}
