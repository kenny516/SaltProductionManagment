package com.analytique.gestion_analytique.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.analytique.gestion_analytique.Models.Employe;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    @Query(value = """
        SELECT e.id, e.nom, e.prenom, ep.idPoste, AVG(ce.niveau) AS moyenne_niveau
        FROM Employes e
        JOIN PostEmploye ep ON e.id = ep.idEmploye
        JOIN CompetencesEmployes ce ON e.id = ce.employe_id
        JOIN detailsPoste dp ON dp.idPoste = ep.idPoste AND ce.competence_id = dp.idCompetence
        WHERE ep.idPoste = :posteId
        GROUP BY e.id, e.nom, e.prenom, ep.idPoste
        HAVING AVG(ce.niveau) > 3;
        """)
    List<Employe> findQualifiedEmployeesForPost(@Param("posteId") Long posteId);
}
