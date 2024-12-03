package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.Candidat;


public interface CandidatRepository extends JpaRepository<Candidat, Integer> {

	@Query(value = "select id from candidats where email = :email and mot_de_passe = :mdp", nativeQuery = true)
	public int candidatExists(@Param("email") String email, @Param("mdp") String motDePasse);

	
}
