package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Integer> {

    @Query(value = """
            SELECT sum(duree)
            FROM conge
            where id_type_conge = 1
              AND extract(YEAR FROM date_debut) >= :anneDebut
              AND extract(YEAR FROM date_fin) <= :anneeFin
              AND id_employe = :idEmploye
            group by id_employe;""", nativeQuery = true)
    double totalCongeByEmploye(@Param("idEmploye") Integer idEmploye, @Param("anneDebut") Integer anneDebut, @Param("anneeFin") Integer anneeFin);

    @Query(value = """
            SELECT *
            FROM conge
            where extract(YEAR FROM date_fin) = :anne
              AND id_employe = :idEmploye
            group by id_employe, id, id_type_conge, date_debut, date_fin, duree, status;""", nativeQuery = true)
    List<Conge> congeParAns(Integer idEmploye,Integer anne);


}
