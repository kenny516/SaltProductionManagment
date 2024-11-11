package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {

	// Pour l'appeler List<Candidat> qualifiedCandidats =
	// candidatRepository.candidatReussiTest("TEST");
	List<Candidat> candidatReussiTest(@Param("nomType") String nomType);
}