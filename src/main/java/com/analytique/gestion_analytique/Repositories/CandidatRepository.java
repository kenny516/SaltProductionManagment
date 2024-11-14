package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.Candidat;

import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
	List<Candidat> findByPosteIdAndStatus(Integer posteId, String status);

	// Pour l'appeler List<Candidat> qualifiedCandidats =
	// candidatRepository.candidatReussiTest("TEST");
	List<Candidat> candidatReussiTest(@Param("nomType") String nomType);



	@Query(value = "select * from candidats_elligibles where poste_id = :posteId",nativeQuery = true)
	public List<Candidat> findElligiblesByPoste(@Param("posteId") Integer posteId);

	@Query(value = "select * from candidats_elligibles",nativeQuery = true)
	public List<Candidat> findElligibles();
}
