package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.Competence;
import com.analytique.gestion_analytique.dto.NoteUser;

public class CandidatSend extends Candidat {
	List<Competence> competences;
	List<NoteUser> notes;

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	

	public CandidatSend(Candidat c,List<Competence> competences,List<NoteUser> notes) {
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

	public List<NoteUser> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteUser> notes) {
		this.notes = notes;
	}

	

	
}
