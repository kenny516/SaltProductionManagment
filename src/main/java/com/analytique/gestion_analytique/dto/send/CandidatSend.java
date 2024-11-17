package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.constants.RecrutementConst;
import com.analytique.gestion_analytique.dto.CompetenceUser;

public class CandidatSend extends Candidat {
	List<CompetenceUser> competences;

	int currentStep;
	double progress;

	public CandidatSend(Candidat c, List<CompetenceUser> competences) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
		setCompetences(competences);
		
		setNotes(c.getNotes());
		setExperiences(c.getExperiences());
		setFormations(c.getFormations());
		setDiplomes(c.getDiplomes());

	}

	public CandidatSend() {
	}

	public List<CompetenceUser> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetenceUser> competences) {
		this.competences = competences;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	@Override
	public void setNotes(List<NoteCandidat> noteCandidat) {
		super.setNotes(noteCandidat);
		setCurrentStep();
	}

	public void setCurrentStep() {
		currentStep = getNotes().size();
		setProgress();
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress() {
		progress = getNotes().size() * 100 / RecrutementConst.nombreEpreuve;
	}

}
