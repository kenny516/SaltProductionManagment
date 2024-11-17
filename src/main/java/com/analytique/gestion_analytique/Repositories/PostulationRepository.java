package com.analytique.gestion_analytique.Repositories;
import com.analytique.gestion_analytique.Models.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
    Optional<Postulation> findByCandidatIdAndStatus(Integer candidatId, String status);

    // Méthode pour trouver les postulations retenues pour un poste donné
    List<Postulation> findByPosteIdAndStatus(Integer posteId, String status);
}

