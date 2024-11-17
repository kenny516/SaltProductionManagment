package com.analytique.gestion_analytique.dto;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.Models.TypeNote;

import jakarta.persistence.EntityManager;

public class NoteUser {
	TypeNote typeNote;
	Integer note;

	public TypeNote getTypeNote() {
		return typeNote;
	}

	public void setTypeNote(TypeNote typeNote) {
		this.typeNote = typeNote;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public NoteUser(NoteCandidat nc) {
		this.typeNote = nc.getTypeNote();
		this.note = nc.getNote();
	}
}
