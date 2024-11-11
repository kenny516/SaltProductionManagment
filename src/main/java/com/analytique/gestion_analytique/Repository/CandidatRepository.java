package com.analytique.gestion_analytique.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.Models.Candidat;

import java.util.List;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    List<Candidat> findByPosteIdAndStatus(Long posteId, String status);
}

