package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.Candidat;

import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	List<Candidat> findByPosteIdAndStatus(Long posteId, String status);

	// Pour l'appeler List<Candidat> qualifiedCandidats =
	// candidatRepository.candidatReussiTest("TEST");
	List<Candidat> candidatReussiTest(@Param("nomType") String nomType);
}
