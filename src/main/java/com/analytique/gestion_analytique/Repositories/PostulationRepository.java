package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
	Optional<Postulation> findByCandidatIdAndStatus(Integer candidatId, String status);

	// Méthode pour trouver les postulations retenues pour un poste donné
	List<Postulation> findByPosteIdAndStatus(Integer posteId, String status);

	Optional<Postulation> findByCandidatIdAndPosteIdAndStatus(Integer candidatId, Integer posteId, String status);
	
	@Modifying
	@Query(value = "UPDATE postulations SET status = :status WHERE id = :id", nativeQuery = true)
	public void updateStatus(@Param("id") Integer id, @Param("status") String status);

	// Méthode pour trouver une postulation par candidat et offre d'emploi
    Postulation findByCandidatIdAndOffreId(Integer candidatId, Integer offreId);

}
