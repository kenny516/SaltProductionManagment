package com.analytique.gestion_analytique.superclasses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.analytique.gestion_analytique.Models.Candidat;
import com.analytique.gestion_analytique.Models.NoteCandidat;
import com.analytique.gestion_analytique.Models.OffreEmploi;
import com.analytique.gestion_analytique.constants.RecrutementConst;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@MappedSuperclass
public class PostulationSuperClass {
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
	public int progress,curentStep;

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
		candidat.nullCandidat();
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



	public List<NoteCandidat> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteCandidat> notes) {
		this.notes = notes;
		setProgress((int)this.notes.size()*100/RecrutementConst.nombreEpreuve);
		setCurentStep(this.notes.size());
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getCurentStep() {
		return curentStep;
	}

	public void setCurentStep(int curentStep) {
		this.curentStep = curentStep;
	}
}
