package com.analytique.gestion_analytique.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "notecandidat")
@IdClass(NoteCandidatId.class)
public class NoteCandidat {
	@Id
	@ManyToOne
	@JoinColumn(name = "idPostulation")
	private Postulation postulation;

	@Id
	@ManyToOne
	@JoinColumn(name = "idtypenote")
	private TypeNote typeNote;

	private Integer note;

	public Postulation getPostulation() {
		return postulation;
	}

	public void setPostulation(Postulation postulation) {
		this.postulation = postulation;
	}

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

}
