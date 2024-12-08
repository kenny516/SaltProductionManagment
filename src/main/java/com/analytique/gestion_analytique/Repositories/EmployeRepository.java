package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.Paye;
import com.analytique.gestion_analytique.Models.PayeDetails;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
	@Query(value = """
			SELECT e.id, e.nom, e.prenom, e.email, e.telephone,e.date_embauche ,ce.id_poste, AVG(ce2.niveau) AS moyenne_niveau
			FROM Employes e
			JOIN ContratEmploye ce ON e.id = ce.id_employe
			JOIN Postes ep ON ep.id = ce.id_poste
			JOIN CompetencesEmployes ce2 ON e.id = ce2.employe_id
			JOIN DetailsPoste dp ON dp.idPoste = ep.id AND ce2.competence_id = dp.idCompetence
			WHERE ep.id = :posteId
			GROUP BY e.id, e.nom, e.prenom, e.email, e.telephone,e.date_embauche ,ce.id_poste
			HAVING AVG(ce2.niveau) > 3
			""", nativeQuery = true)
	List<Employe> findQualifiedEmployeesForPost(@Param("posteId") Integer posteId);

	@Query(value="SELECT * FROM paye_details where mois = :mois and annee = :annee and id_employe = :id_employe", nativeQuery = true) 
	PayeDetails getPaye(@Param("mois") Integer mois, @Param("annee") Integer annee, @Param("id_employe") Integer id_employe);
	
	@Query(value = "SELECT calculer_irsa(:idEmploye)", nativeQuery = true)
    Double calculerIrsa(@Param("idEmploye") Integer idEmploye);
}
