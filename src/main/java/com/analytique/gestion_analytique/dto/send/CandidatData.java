package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.Competence;

public class CandidatData extends Candidat {
	List<Competence> competences;

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public CandidatData(Candidat c,List<Competence> competences) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setDateCandidature(c.getDateCandidature());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
		setPoste(c.getPoste());
		setStatus(c.getStatus());
		this.competences = competences;
	}

	public CandidatData(){}

	
}
