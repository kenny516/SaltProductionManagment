package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
	Optional<Postulation> findByCandidatIdAndStatus(Integer candidatId, String status);

	// Méthode pour trouver les postulations retenues pour un poste donné
	List<Postulation> findByPosteIdAndStatus(Integer posteId, String status);

	@Query(value = "select * from candidats_elligibles where poste_id = :posteId", nativeQuery = true)
	public List<Postulation> findElligiblesByPoste(@Param("posteId") Integer posteId);

	@Query(value = "select * from candidats_elligibles", nativeQuery = true)
	public List<Postulation> findElligibles();

}
