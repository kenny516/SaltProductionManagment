package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.Models.CandidatsDiplomes;
import com.analytique.gestion_analytique.Models.CandidatsDiplomesId;

public interface CandidatsDiplomeRepo extends JpaRepository<CandidatsDiplomes,CandidatsDiplomesId>{
	
}
