package com.analytique.gestion_analytique.dto.receive;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.Poste;
import com.analytique.gestion_analytique.dto.CompetenceUser;

import jakarta.persistence.EntityManager;

public class CandidatRecieve {
	String nom, prenom, email, telephone;
	int poste_id;
	LocalDateTime dateCandidature;
	List<CompetenceUser> competences;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getPoste_id() {
		return poste_id;
	}

	public void setPoste_id(int poste_id) {
		this.poste_id = poste_id;
	}

	public LocalDateTime getDateCandidature() {
		return dateCandidature;
	}

	public void setDateCandidature(LocalDateTime candidaturTime) {
		this.dateCandidature = candidaturTime;
	}

	

	public CandidatRecieve(String nom, String prenom, String email, String telephone, int poste_id,
			LocalDateTime candidaturTime, List<CompetenceUser> competences) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.poste_id = poste_id;
		this.dateCandidature = candidaturTime;
		this.competences = competences;
	}

	public Candidat extractCandidat(EntityManager em) {
		Candidat c = new Candidat();
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setDateCandidature(dateCandidature.toLocalDate());
		c.setEmail(email);
		c.setTelephone(telephone);
		c.setPoste(em.getReference(Poste.class, poste_id));
		return c;
	}

	public List<CompetencesCandidats> extractCCandidat(EntityManager em) {
		List<CompetencesCandidats> csc = new ArrayList<CompetencesCandidats>();

		for (CompetenceUser competences : getCompetences()) {
			CompetencesCandidats cc = competences.extractCandidat(em);
			csc.add(cc);
		}
		return csc;
	}

	public List<CompetenceUser> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetenceUser> competences) {
		this.competences = competences;
	}

}
