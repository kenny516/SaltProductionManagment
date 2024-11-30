package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.SoldeConge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SoldeCongeRepository extends JpaRepository<SoldeConge, Integer> {

    @Query(value = """
             SELECT count(mois) * 2.5
            from soldeconge
            where id_type_conge = :idTypeConge
              AND annee >= :anneeDebut
              AND annee_fin <= :anneeFin
              AND id_employe = :idEmploye
            group by id_employe;""", nativeQuery = true)
    Double congePossible(@Param("idTypeConge") Integer idTypeConge,@Param("idEmploye")Integer idEmploye,@Param("anneeDebut") Integer anneeDebut,@Param("anneeFin") Integer anneeFin);

}


