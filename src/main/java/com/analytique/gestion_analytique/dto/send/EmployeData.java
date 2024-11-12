package com.analytique.gestion_analytique.dto.send;

import java.time.LocalDate;
import java.util.List;

import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.Employe;
import com.analytique.gestion_analytique.Models.Poste;

public class EmployeData extends Employe{
	List<Competence> competences;

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public EmployeData(String nom, String prenom, String email, String telephone, LocalDate dateEmbauche, Poste poste,
			List<Competence> competences) {
		super(nom, prenom, email, telephone, dateEmbauche, poste);
		this.competences = competences;
	}

	public EmployeData(List<Competence> competences) {
		this.competences = competences;
	}

	public EmployeData(){}

	public static EmployeData map(Employe e,List<Competence> competences){
		return new EmployeData(e.getNom(),e.getPrenom(),e.getEmail(),e.getTelephone(),e.getDateEmbauche(),e.getPoste(),competences);
	}
}
