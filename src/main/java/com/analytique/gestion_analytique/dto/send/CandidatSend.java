package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;

public class CandidatSend extends Candidat {
	List<CompetencesCandidats> competences;

	public CandidatSend(Candidat c) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
		setCompetences(c.getCompetencesCandidats());
		setExperiences(c.getExperiences());
		setFormations(c.getFormations());
		setDiplomes(c.getDiplomes());
		setPostulations(c.getPostulations());
	}

	public CandidatSend() {
	}

	public List<CompetencesCandidats> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetencesCandidats> competences) {
		this.competences = competences;
	}

}
