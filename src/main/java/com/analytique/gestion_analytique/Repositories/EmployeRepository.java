package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.analytique.gestion_analytique.Models.Employe;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    @Query(value = """
        SELECT e.id, e.nom, e.prenom, ep.poste.id, AVG(ce.niveau) AS moyenne_niveau
        FROM Employe e
        JOIN PostEmploye ep ON e.id = ep.employe.id
        JOIN CompetencesEmployes ce ON e.id = ce.employe.id
        JOIN DetailsPoste dp ON dp.poste.id = ep.poste.id AND ce.competence.id = dp.competence.id
        WHERE ep.poste.id = :posteId
        GROUP BY e.id, e.nom, e.prenom, ep.poste.id
        HAVING AVG(ce.niveau) > 3
        """)
    List<Employe> findQualifiedEmployeesForPost(@Param("posteId") Integer posteId);
}
