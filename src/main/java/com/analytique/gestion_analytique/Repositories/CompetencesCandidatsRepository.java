package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.CompetencesCandidatsId;

public interface CompetencesCandidatsRepository extends JpaRepository<CompetencesCandidats, CompetencesCandidatsId> {
    List<CompetencesCandidats> findByCandidatId(Long candidatId);
    
} 