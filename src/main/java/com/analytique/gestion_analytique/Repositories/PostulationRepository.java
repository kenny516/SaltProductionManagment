package com.analytique.gestion_analytique.Repositories;
import com.analytique.gestion_analytique.Models.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
    Optional<Postulation> findByCandidatIdAndStatus(Integer candidatId, String status);
}

