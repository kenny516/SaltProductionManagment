package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.CompetencesCandidats;
import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.constants.RecrutementConst;

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
		
		setNotes(c.getNotes());
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
