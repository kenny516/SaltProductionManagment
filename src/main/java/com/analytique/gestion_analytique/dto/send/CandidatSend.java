package com.analytique.gestion_analytique.dto.send;

import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.dto.CompetenceUser;
import com.analytique.gestion_analytique.dto.NoteUser;

public class CandidatSend extends Candidat {
	List<CompetenceUser> competences;
	List<NoteUser> notes;


	public CandidatSend(Candidat c,List<CompetenceUser> competences,List<NoteUser> notes) {
		setId(c.getId());
		setNom(c.getNom());
		setPrenom(c.getPrenom());
		setEmail(c.getEmail());
		setTelephone(c.getTelephone());
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

	public List<CompetenceUser> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetenceUser> competences) {
		this.competences = competences;
	}

	

	
}
