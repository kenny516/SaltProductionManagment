package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
  
}
