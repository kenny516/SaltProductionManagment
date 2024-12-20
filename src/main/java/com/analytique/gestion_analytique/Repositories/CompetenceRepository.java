package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.Competence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
  String getbyposteString = "select * from Competences where id in (select idCompetence from detailsPoste where idPoste = :idPoste)";
  String getbyEmployeString = "select * from Competences where id in (select competence_id from competencesemployes where employe_id = :idEmploye)";
	
	@Query(value = getbyposteString , nativeQuery = true)
	List<Competence> findByPoste(@Param("idPoste") Integer idPoste);

	@Query(value = getbyEmployeString , nativeQuery = true)
	List<Competence> findByEmploye(@Param("idEmploye") Integer idEmploye);
}
