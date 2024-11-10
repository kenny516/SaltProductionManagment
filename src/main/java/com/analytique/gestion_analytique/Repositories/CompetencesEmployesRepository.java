package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.CompetencesCandidatsId;
import com.analytique.gestion_analytique.Models.CompetencesEmployes;
import com.analytique.gestion_analytique.Models.CompetencesEmployesId;

public interface CompetencesEmployesRepository extends JpaRepository<CompetencesEmployes, CompetencesEmployesId> {

    
} 