package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;

public class CandidatSend extends Candidat {
	List<CompetencesCandidats> competences;

	int currentStep;
	double progress;

	public CandidatSend(Candidat c, List<CompetencesCandidats> competences) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
		setCompetences(competences);
		setExperiences(c.getExperiences());
		setFormations(c.getFormations());
		setDiplomes(c.getDiplomes());
		setPostulations(c.getPostulations());

	}

	public CandidatSend() {
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public double getProgress() {
		return progress;
	}

	

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public List<CompetencesCandidats> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetencesCandidats> competences) {
		this.competences = competences;
	}

}
