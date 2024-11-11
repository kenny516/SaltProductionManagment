package com.analytique.gestion_analytique.dto.send;

import java.time.LocalDate;
import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.Poste;

import jakarta.persistence.EntityManager;

public class CandidatureData {
	String nom,prenom,email,telephone,status;
	LocalDate dateCandidature;
	Integer poste;

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDateCandidature() {
		return dateCandidature;
	}
	public void setDateCandidature(LocalDate dateCandidature) {
		this.dateCandidature = dateCandidature;
	}
	public Integer getPoste() {
		return poste;
	}
	public void setPoste(Integer poste) {
		this.poste = poste;
	}
	public CandidatureData(String nom, String prenom, String email, String telephone, String status,
			LocalDate dateCandidature, Integer poste) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.status = status;
		this.dateCandidature = dateCandidature;
		this.poste = poste;
	}

	public Candidat extractCandidat(EntityManager em){
		Candidat c = new Candidat();


		return c;
	}

}
