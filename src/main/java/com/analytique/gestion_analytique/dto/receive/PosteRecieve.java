package com.analytique.gestion_analytique.dto.receive;

import java.util.List;
import java.util.ArrayList;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.DetailsPoste;
import com.analytique.gestion_analytique.Models.Poste;

import jakarta.persistence.EntityManager;

public class PosteRecieve extends Poste{
	List<Integer> competences;

	public List<Integer> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Integer> competences) {
		this.competences = competences;
	}

	public PosteRecieve(){
		super();
	}

	public List<DetailsPoste> extractDetails(EntityManager em){
		List<DetailsPoste> details = new ArrayList<>();
		for (Integer competence : competences) {
			DetailsPoste d = new DetailsPoste();
			d.setCompetence(em.getReference(Competence.class,competence));
			details.add(d);
		}
		return details;
	}
}
