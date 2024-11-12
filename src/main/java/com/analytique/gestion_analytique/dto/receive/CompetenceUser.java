package com.analytique.gestion_analytique.dto.receive;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.CompetencesEmployes;

import jakarta.persistence.EntityManager;

public class CompetenceUser {
	int id;
	int niveau;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public CompetenceUser(int id, int niveau) {
		this.id = id;
		this.niveau = niveau;
	}

	public CompetencesCandidats extractCandidat(EntityManager em){
		CompetencesCandidats cc = new CompetencesCandidats();
		cc.setCompetence(em.getReference(Competence.class, getId()));
		cc.setNiveau(getNiveau());
		return cc;
	}

	public CompetencesEmployes extractEmployes(EntityManager em){
		CompetencesEmployes ce = new CompetencesEmployes();
		ce.setCompetence(em.getReference(Competence.class, getId()));
		ce.setNiveau(getNiveau());

		return ce;
	}
}
