package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.Models.NoteCandidat;

public class CandidatSend extends Candidat {
	List<Competence> competences;
	List<NoteCandidat> notes;

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	

	public CandidatSend(Candidat c,List<Competence> competences,List<NoteCandidat> notes) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setDateCandidature(c.getDateCandidature());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
		setPoste(c.getPoste());
		setStatus(c.getStatus());
		setCompetences(competences);
		setNotes(notes);
	}

	public CandidatSend(){}

	public List<NoteCandidat> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteCandidat> notes) {
		this.notes = notes;
	}

	
}
