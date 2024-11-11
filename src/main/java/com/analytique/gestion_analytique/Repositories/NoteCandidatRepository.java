package com.analytique.gestion_analytique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.Models.NoteCandidatId;

public interface NoteCandidatRepository extends JpaRepository<NoteCandidat, NoteCandidatId> {

}