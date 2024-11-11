package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.analytique.gestion_analytique.Models.Employe;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    @Query(value = """
        SELECT e.id, e.nom, e.prenom, ep.idposte, AVG(ce.niveau) AS moyenne_niveau
        FROM Employes e
        JOIN postemploye ep ON e.id = ep.idemploye
        JOIN competencesemployes ce ON e.id = ce.employe_id
        JOIN detailsposte dp ON dp.idposte = ep.idposte AND ce.competence_id = dp.idcompetence
        WHERE ep.idposte = :posteId
        GROUP BY e.id, e.nom, e.prenom, ep.idposte
        HAVING AVG(ce.niveau) > 3
        """)
    List<Employe> findQualifiedEmployeesForPost(@Param("posteId") Long posteId);
}
