package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.Poste;

public class PosteSend extends Poste {
	List<Competence>  competences;

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	
}
