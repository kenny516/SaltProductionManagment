package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.constants.RecrutementConst;
import com.analytique.gestion_analytique.dto.CompetenceUser;
import com.analytique.gestion_analytique.dto.NoteUser;

public class CandidatSend extends Candidat {
	List<CompetenceUser> competences;
	List<NoteUser> notes;

	int currentStep;
	double progress;

	public CandidatSend(Candidat c, List<CompetenceUser> competences, List<NoteUser> notes) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
		setCompetences(competences);
		setNotes(notes);
	}

	public CandidatSend() {
	}

	public List<NoteUser> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteUser> notes) {
		this.notes = notes;
		setCurrentStep();
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
		progress = notes.size() * 100 / RecrutementConst.nombreEpreuve;
	}

}
