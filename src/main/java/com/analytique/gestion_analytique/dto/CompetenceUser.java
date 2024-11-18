package com.analytique.gestion_analytique.dto;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.CompetencesEmployes;

import jakarta.persistence.EntityManager;

public class CompetenceUser extends Competence {
	int idCompetence;
	int niveau;

	
	public CompetenceUser() {
	}

	public CompetenceUser(Competence c, int candidat_id,int niveau) {
		setId(c.getId());
		setNom(c.getNom());
		setDescription(c.getDescription());
		setNiveau(niveau);
	}

	public CompetenceUser(int id,int niveau){
		setId(id);
		setNiveau(niveau);
	}

	public CompetencesCandidats extractCandidat(EntityManager em) {
		CompetencesCandidats cc = new CompetencesCandidats();
		cc.setCompetence(em.getReference(Competence.class, getIdCompetence()));
		cc.setNiveau(getNiveau());
		return cc;
	}

	public CompetencesEmployes extractEmployes(EntityManager em) {
		CompetencesEmployes ce = new CompetencesEmployes();
		ce.setCompetence(em.getReference(Competence.class, getIdCompetence()));
		ce.setNiveau(getNiveau());
		return ce;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getIdCompetence() {
		return idCompetence;
	}

	public void setIdCompetence(int idCompetence) {
		this.idCompetence = idCompetence;
	}

	
}
