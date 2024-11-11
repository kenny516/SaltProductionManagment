package com.analytique.gestion_analytique.dto.send;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;

import jakarta.persistence.EntityManager;

public class CandidatureData {
	Candidat candidat;
	List<CompetencesCandidats> competences;

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public List<CompetencesCandidats> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetencesCandidats> competence) {
		this.competences = competence;
	}

	public CandidatureData(Candidat candidat, List<CompetencesCandidats> competence) {
		this.candidat = candidat;
		this.competences = competence;
	}

}
