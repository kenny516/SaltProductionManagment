package com.analytique.gestion_analytique.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.analytique.gestion_analytique.constants.RecrutementConst;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Postulations")
public class Postulation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "candidat_id", nullable = false)
	@JsonBackReference
	private Candidat candidat;

	@ManyToOne
	@JoinColumn(name = "Offre_emploi_id", nullable = false)
	private OffreEmploi offreEmploi;

	private String status = "En attente";

	private LocalDate datePostulation = LocalDate.now();

	@OneToMany(mappedBy = "postulation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NoteCandidat> notes = new ArrayList<>();

	@Transient
	private int progress;

	@Transient
	private int currentStep;

	public Postulation(Candidat candidat, OffreEmploi offre, LocalDate datePostulation) {
		this.candidat = candidat;
		this.offreEmploi = offre;
		this.datePostulation = datePostulation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public OffreEmploi getOffreEmploi() {
		return offreEmploi;
	}

	public void setOffreEmploi(OffreEmploi poste) {
		this.offreEmploi = poste;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDatePostulation() {
		return datePostulation;
	}

	public void setDatePostulation(LocalDate datePostulation) {
		this.datePostulation = datePostulation;
	}

	public Postulation() {
	}

	public List<NoteCandidat> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteCandidat> notes) {
		this.notes = notes;
	}

	public int getProgress() {
		return notes.size() == 0 ? 0
				: (notes.size() * 100) / RecrutementConst.nombreEpreuve;
	}

	public int getCurrentStep() {
		return notes.size();
	}
}
