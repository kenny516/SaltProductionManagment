package com.analytique.gestion_analytique.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.Models.NoteCandidatId;

public interface NoteCandidatRepository extends JpaRepository<NoteCandidat, NoteCandidatId> {

	@Query(value = " select * from notecandidat nc where nc.idcandidat = :candidatId ", nativeQuery = true)
	public List<NoteCandidat> findByCandidat(@Param("candidatId") Integer candidatId);
}
