package com.analytique.gestion_analytique.dto;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.CompetencesEmployes;

import jakarta.persistence.EntityManager;

public class CompetenceUser extends Competence {
	int niveau;

	public CompetenceUser() {
	}

	public CompetenceUser(Competence c, int niveau) {
		setId(c.getId());
		setNom(c.getNom());
		setDescription(c.getDescription());
		setNiveau(niveau);

	}

	public CompetencesCandidats extractCandidat(EntityManager em) {
		CompetencesCandidats cc = new CompetencesCandidats();
		cc.setCompetence(em.getReference(Competence.class, getId()));
		cc.setNiveau(getNiveau());
		return cc;
	}

	public CompetencesEmployes extractEmployes(EntityManager em) {
		CompetencesEmployes ce = new CompetencesEmployes();
		ce.setCompetence(em.getReference(Competence.class, getId()));
		ce.setNiveau(getNiveau());

		return ce;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
}
