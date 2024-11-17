package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.Candidat;

import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
	// Pour l'appeler List<Candidat> qualifiedCandidats =
	// candidatRepository.candidatReussiTest("TEST");
	List<Candidat> candidatReussiTest(@Param("nomType") String nomType);

	@Query(value = "select * from candidats_postules", nativeQuery = true)
	List<Candidat> findAllPostule();

	
}
