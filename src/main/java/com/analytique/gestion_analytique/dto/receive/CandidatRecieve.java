package com.analytique.gestion_analytique.dto.receive;

import java.util.ArrayList;
import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CandidatsDiplomes;
import com.analytique.gestion_analytique.Models.Diplome;
import com.analytique.gestion_analytique.Models.Experience;
import com.analytique.gestion_analytique.Models.Formation;

public class CandidatRecieve {
	String nom, prenom, email, telephone, motDePasse;
	List<Formation> formations;
	List<Experience> experiences;
	List<Integer> diplomes;

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

	public void setTelephone(String telepohne) {
		this.telephone = telepohne;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Integer> getDiplomes() {
		return diplomes;
	}

	public void setDiplomes(List<Integer> diplomes) {
		this.diplomes = diplomes;
	}

	public Candidat extractCandidat() {
		Candidat c = new Candidat();
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setEmail(email);
		c.setTelephone(telephone);
		c.setMotDePasse(motDePasse);
		return c;
	}

	public List<CandidatsDiplomes> extractDiplomes() {
		List<CandidatsDiplomes> dips = new ArrayList<>();

		for (Integer diplome : getDiplomes()) {
			Diplome m = new Diplome();
			m.setId(diplome);
			CandidatsDiplomes cd = new CandidatsDiplomes();
			cd.setDiplome(m);

			dips.add(cd);
		}

		return dips;
	}

}
