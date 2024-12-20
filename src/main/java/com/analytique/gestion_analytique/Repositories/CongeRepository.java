package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Integer> {

        List<Conge> findByEmployeIdAndIdTypeCongeEstPayeFalse(Integer employeId);

    @Query(value = """
            SELECT sum(duree)
            FROM conge
            where id_type_conge = :idTypeConge
              AND extract(YEAR FROM date_debut) >= :anneDebut
              AND extract(YEAR FROM date_fin) <= :anneeFin
              AND id_employe = :idEmploye
            group by id_employe;""", nativeQuery = true)
    Double totalCongeByEmploye(@Param("idTypeConge") Integer idTypeConge, @Param("idEmploye") Integer idEmploye, @Param("anneDebut") Integer anneDebut, @Param("anneeFin") Integer anneeFin);

    @Query(value = """
            SELECT *
            FROM conge
            where extract(YEAR FROM date_fin) = :anne
              AND id_employe = :idEmploye
            group by id_employe, id, id_type_conge, date_debut, date_fin, duree, status;""", nativeQuery = true)
    List<Conge> congeParAns(Integer idEmploye, Integer anne);

    @Query(value = """
            SELECT *
            FROM conge
            where id_employe = :idEmploye
            AND id_type_conge = :idTypeConge
            group by id_employe, id, id_type_conge, date_debut, date_fin, duree, status;""", nativeQuery = true)
    List<Conge> findByEmployeId(Integer idTypeConge,Integer idEmploye);

    @Query(value = """
            WITH CalculConge AS (
                SELECT id_employe,
                       GREATEST(date_debut, DATE_TRUNC('month', TO_DATE(:annee || '-' || :mois, 'YYYY-MM'))) AS debut_effectif,
                       LEAST(date_fin, DATE_TRUNC('month', TO_DATE(:annee || '-' || :mois, 'YYYY-MM')) + INTERVAL '1 month' - INTERVAL '1 day') AS fin_effectif
                FROM Conge
                WHERE DATE_PART('year', date_debut) <= :annee
                  AND DATE_PART('year', date_fin) >= :annee
                  AND DATE_PART('month', date_debut) <= :mois
                  AND DATE_PART('month', date_fin) >= :mois
                  AND id_employe = :idEmploye
            )
            SELECT COALESCE(SUM(EXTRACT(DAY FROM (fin_effectif - debut_effectif)) + 1), 0) AS jours_conge
            FROM CalculConge;
            """, nativeQuery = true)
    Double nbrJourCongeParMois(@Param("idEmploye") Integer idEmploye,
                               @Param("mois") Integer mois,
                               @Param("annee") Integer annee);


    @Query(value = """
            WITH CalculConge AS (
                SELECT id_employe,
                       GREATEST(date_debut, DATE_TRUNC('month', TO_DATE(:annee || '-' || :mois, 'YYYY-MM'))) AS debut_effectif,
                       LEAST(date_fin, DATE_TRUNC('month', TO_DATE(:annee || '-' || :mois, 'YYYY-MM')) + INTERVAL '1 month' - INTERVAL '1 day') AS fin_effectif
                FROM Conge JOIN typeconge  ON typeconge.id = conge.id_type_conge
                WHERE DATE_PART('year', date_debut) <= :annee
                  AND DATE_PART('year', date_fin) >= :annee
                  AND DATE_PART('month', date_debut) <= :mois
                  AND DATE_PART('month', date_fin) >= :mois
                  AND id_employe = :idEmploye
                  AND typeconge.est_paye = true
            )
            SELECT COALESCE(SUM(EXTRACT(DAY FROM (fin_effectif - debut_effectif)) + 1), 0) AS jours_conge
            FROM CalculConge;
            """, nativeQuery = true)
    Double nbrJourCongeParMoisPaye(@Param("idEmploye") Integer idEmploye,
                               @Param("mois") Integer mois,
                               @Param("annee") Integer annee);

        @Query("SELECT SUM(CAST(DATEDIFF(DAY, c.dateDebut, c.dateFin) + 1 AS double)) " +
        "FROM Conge c WHERE c.employe.id = :idEmploye AND " +
        "MONTH(c.dateDebut) = :mois AND YEAR(c.dateDebut) = :annee AND " +
        "c.idTypeConge.estPaye = false")
        Double nbrJourCongeNonPayeParMois(@Param("idEmploye") Integer idEmploye, 
                                          @Param("mois") Integer mois, 
                                          @Param("annee") Integer annee);
                        

}
