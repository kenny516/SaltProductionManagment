package com.analytique.gestion_analytique.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.V_CandidatPostulation;

public interface V_CandidatPostulationRepository extends JpaRepository<V_CandidatPostulation,Integer>{
	@Query(value = "select * from candidats_elligibles where poste_id = :posteId", nativeQuery = true)
	public List<V_CandidatPostulation> findElligiblesByPoste(@Param("posteId") Integer posteId);

	@Query(value = "select * from candidats_elligibles", nativeQuery = true)
	public List<V_CandidatPostulation> findElligibles();

	@Query(value = "select * from candidats_postules", nativeQuery = true)
	List<V_CandidatPostulation> findNonEmploye();

	@Query(value = "select * from candidats_postules", nativeQuery = true)
	List<V_CandidatPostulation> findAllPostule();

	@Query(value = "select * from candidats_postules where status <> 'Refus' and offre_status is true", nativeQuery = true)
	List<V_CandidatPostulation> findAllNonRefus();

	List<V_CandidatPostulation> findByOffreIdAndOffreStatusAndStatus(Integer offreId,Boolean offreStatus,String status);
}
