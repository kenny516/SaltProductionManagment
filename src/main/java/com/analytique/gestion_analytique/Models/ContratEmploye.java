package com.analytique.gestion_analytique.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Contratemploye")
public class ContratEmploye {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_employe")
	@JsonBackReference
	private Employe employe;

	@ManyToOne
	@JoinColumn(name = "id_poste")
	private Poste poste;

	@ManyToOne
	@JoinColumn(name = "id_type_contrat")
	private TypeContrat typeContrat;

	@Column(name = "date_debut")
	private LocalDate dateDebut;

	@Column(name = "date_fin")
	private LocalDate dateFin;

	@Column(name = "salaire", precision = 20, scale = 2)
	private BigDecimal salaire;

	@Column(name = "taux_horaire", precision = 20, scale = 2)
	private BigDecimal tauxHoraire;

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		// employe.setContrat(null);
		this.employe = employe;
	}

	public TypeContrat getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(TypeContrat contrat) {
		this.typeContrat = contrat;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getSalaire() {
		return salaire;
	}

	public void setSalaire(BigDecimal salaire) {
		this.salaire = salaire;
	}

	public BigDecimal getTauxHoraire() {
		if (tauxHoraire == null) {
			setTauxHoraire(this.salaire.divide(new BigDecimal("173.33"), 2, RoundingMode.HALF_UP));
		}
		return tauxHoraire;
	}

	public void setTauxHoraire(BigDecimal tauxHoraire) {
		this.tauxHoraire = tauxHoraire;
	}

	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public ContratEmploye modify(LocalDate date_debut, TypeContrat contrat, Poste poste,BigDecimal nouveauSalaire) {
		ContratEmploye ce = new ContratEmploye();
		if (date_debut == null && this.dateFin == null) {
			this.dateFin = LocalDate.now();
			ce.setDateDebut(LocalDate.now());
		} else if (date_debut == null && this.dateFin != null) {
			ce.setDateDebut(this.dateFin);
		} else if (date_debut != null && this.dateFin == null) {
			ce.setDateDebut(date_debut);
			this.dateFin = date_debut;
		} else {
			ce.setDateDebut(date_debut);
		}
		ce.setPoste(poste == null ? this.getPoste() : poste);
		ce.setTypeContrat(contrat == null ? this.getTypeContrat() : contrat);
		ce.setSalaire(nouveauSalaire == null ? this.getSalaire() : nouveauSalaire);
		ce.setEmploye(employe);
		return ce;
	}
}
